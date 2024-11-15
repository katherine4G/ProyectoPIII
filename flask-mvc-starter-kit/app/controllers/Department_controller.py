from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Department import Department
from app.models.Faculty import Faculty
from app.models.University import University
from app import db

@jwt_required()
def create():
    data = request.get_json()
    try:
        nameDepartment = data.get('nameDepartment')
        facultyId = data.get('facultyId')

        if not all([nameDepartment, facultyId]):
            return jsonify({"Error": -1, "msg": "Todos los campos son necesarios"}), 400

        faculty = Faculty.query.get(facultyId)
        if not faculty:
            return jsonify({"Error": -1, "msg": "Facultad no encontrada"}), 404

        new_department = Department(
            nameDepartment=nameDepartment,
             faculty=faculty
        )
        db.session.add(new_department)
        db.session.commit()
        return jsonify({"code": 1, "msg": "Departamento creado exitosamente", "department": new_department.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear el departamento: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear el departamento"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    department_id = data.get("idDepartment")

    if not department_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    department = Department.query.get(department_id)
    if not department:
        return jsonify({"Error": -1, "msg": "Departamento no encontrado"}), 404

    db.session.delete(department)
    db.session.commit()
    return jsonify({"code": 1, "msg": "Departamento eliminado exitosamente"}), 200

@jwt_required()
def update():
    data = request.get_json()
    department_id = data.get("idDepartment")
    name_department = data.get("nameDepartment")
    faculty_id = data.get("facultyId")

    if not department_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    department = Department.query.get(department_id)
    if not department:
        return jsonify({"Error": -1, "msg": "Departamento no encontrado"}), 404

    department.nameDepartment = name_department or department.nameDepartment
    department.facultyId = faculty_id or department.facultyId
    db.session.commit()

    return jsonify({"code": 1, "msg": "Departamento actualizado exitosamente", "department": department.to_dict()}), 200

@jwt_required()
def showAll():
    departments = Department.query.all()
    return jsonify([department.to_dict() for department in departments]), 200

@jwt_required()
def showID():
    department_id = request.args.get("idDepartment")

    if not department_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    department = Department.query.get(department_id)
    if not department:
        return jsonify({"Error": -1, "msg": "Departamento no encontrado"}), 404

    return jsonify({"code": 1, "msg": "Departamento encontrado", "department": department.to_dict()}), 200

@jwt_required()
def showAllWithFaculty():
    departments_with_faculty = db.session.query(Department).join(Faculty).all()

    result = [
        {
            "idDepartment": department.idDepartment,
            "nameDepartment": department.nameDepartment,
            "faculty": department.faculty.to_dict() if department.faculty else None
        }
        for department in departments_with_faculty
    ]

    return jsonify(result), 200

@jwt_required()
def showAllWithFacultyAndUniversity():
    departments = db.session.query(Department).join(Faculty).join(University).all()

    result = [
        {
            "idDepartment": department.idDepartment,
            "nameDepartment": department.nameDepartment,
            "faculty": {
                "facultyId": department.faculty.facultyId,
                "facultyName": department.faculty.facultyName,
                "facultyType": department.faculty.facultyType,
                "university": {
                    "universityId": department.faculty.university.universityId,
                    "universityName": department.faculty.university.universityName,
                    "uniCountry": department.faculty.university.uniCountry,
                    "uniSede": department.faculty.university.uniSede,
                }
            } if department.faculty and department.faculty.university else None
        }
        for department in departments
    ]
    return jsonify(result), 200

def show_faculties_by_university(university_id):
    try:
        university = University.query.get(university_id)
        if not university:
            return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404
        
        faculties = Faculty.query.filter_by(universityId=university_id).all()
        result = [faculty.to_dict() for faculty in faculties]

        return jsonify({"code": 1, "faculties": result}), 200

    except Exception as e:
        print(f"Error al obtener facultades por universidad: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener facultades"}), 500
