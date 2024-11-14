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
           # facultyId=facultyId
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
#@route_dept.route('/showAllWithFacultyAndUniversity', methods=['GET'])
def showAllWithFacultyAndUniversity():
    faculties_with_university = db.session.query(Faculty).join(University).all()

    result = [
        {
            "facultyId": faculty.facultyId,
            "facultyName": faculty.facultyName,
            "facultyType": faculty.facultyType,
            "university": faculty.university.to_dict() if faculty.university else None
        }
        for faculty in faculties_with_university
    ]

    return jsonify(result), 200

# def showAllWithFacultyAndUniversity():
#     # Realiza la consulta uniendo las tres tablas: Department, Faculty, y University
#     departments_with_faculty_university = (
#         db.session.query(Department)
#         .join(Faculty, Department.facultyId == Faculty.facultyId)
#         .join(University, Faculty.universityId == University.idUniversity)
#         .all()
#     )

#     # Construye el resultado con los datos del departamento, facultad, y universidad
#     result = [
#         {
#             "idDepartment": department.idDepartment,
#             "nameDepartment": department.nameDepartment,
#             "faculty": {
#                 "idFaculty": department.faculty.facultyId,
#                 "nameFaculty": department.faculty.nameFaculty,
#                 "university": {
#                     "idUniversity": department.faculty.university.idUniversity,
#                     "nameUniversity": department.faculty.university.nameUniversity,
#                 }
#             }
#         }
#         for department in departments_with_faculty_university
#     ]

#     return jsonify(result), 200
