from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Enrollment_Professor import Enrollment_Professor
from app.models.user import User
from app.models.Course import Course
from app.models.Department import Department
from app.models.Faculty import Faculty
from app.models.University import University
from app import db

@jwt_required()
def create():
    data = request.get_json()
    id_professor = data.get('id_professor')
    NRC = data.get('NRC')

    if not all([id_professor, NRC]):
        return jsonify({"Error": -1, "msg": "ID del profesor y NRC son necesarios"}), 400

    new_enrollment = Enrollment_Professor(id_professor=id_professor, NRC=NRC)

    try:
        with db.session.begin():
            db.session.add(new_enrollment)

        return jsonify({"code": 1, "msg": "Inscripción del profesor creada exitosamente", "enrollment": new_enrollment.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la inscripción del profesor: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la inscripción del profesor"}), 500
    
@jwt_required()
def delete():
    data = request.get_json()
    enroll_profeId = data.get("enroll_profeId")

    if not enroll_profeId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        # Busca la inscripción del profesor por ID
        enrollment = Enrollment_Professor.query.get(enroll_profeId)
        if not enrollment:
            return jsonify({"Error": -1, "msg": "Inscripción del profesor no encontrada"}), 404

        # Elimina la inscripción
        db.session.delete(enrollment)
        db.session.commit()  # Guarda los cambios en la base de datos

        return jsonify({"code": 1, "msg": "Inscripción del profesor eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()  # Revertir cambios si hay un error
        print(f"Error al eliminar la inscripción del profesor: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la inscripción del profesor"}), 500
    
@jwt_required()
def update():
    data = request.get_json()
    enroll_profeId = data.get("enroll_profeId")
    new_id_professor = data.get("new_id_professor")
    new_NRC = data.get("new_NRC")

    if not enroll_profeId or not new_id_professor or not new_NRC:
        return jsonify({"Error": -1, "msg": "ID y todos los nuevos valores son necesarios"}), 400

    try:
        # Verificar si el nuevo id_professor existe
        professor = User.query.get(new_id_professor)
        if not professor:
            return jsonify({"Error": -1, "msg": "El ID del profesor no existe"}), 400

        # Verificar si el nuevo NRC existe
        course = Course.query.get(new_NRC)
        if not course:
            return jsonify({"Error": -1, "msg": "El NRC no existe"}), 400

        enrollment = Enrollment_Professor.query.get(enroll_profeId)
        if not enrollment:
            return jsonify({"Error": -1, "msg": "Inscripción del profesor no encontrada"}), 404

        enrollment.id_professor = new_id_professor
        enrollment.NRC = new_NRC

        db.session.commit()

        return jsonify({"code": 1, "msg": "Inscripción del profesor actualizada exitosamente", "enrollment": enrollment.to_dict()}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar la inscripción del profesor: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la inscripción del profesor"}), 500
    
@jwt_required()
def showAll():
    try:
        enrollments = Enrollment_Professor.query.all()
        return jsonify([enrollment.to_dict() for enrollment in enrollments]), 200
    except Exception as e:
        print(f"Error al obtener todas las inscripciones de profesores: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener inscripciones"}), 500
    
@jwt_required()
def showID():
    enroll_profeId = request.args.get("enroll_profeId")

    if not enroll_profeId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        enroll_profeId = int(enroll_profeId) 
    except ValueError:
        return jsonify({"Error": -1, "msg": "ID debe ser un número entero"}), 400

    enrollment = Enrollment_Professor.query.get(enroll_profeId)
    if not enrollment:
        return jsonify({"Error": -1, "msg": "Inscripción del profesor no encontrada"}), 404

    return jsonify({"code": 1, "msg": "Inscripción del profesor encontrada", "enrollment": enrollment.to_dict()}), 200

@jwt_required()
def showAllEnrollmentsProfessorsWithAllDetails():
    enrollments = db.session.query(Enrollment_Professor).join(User).join(Course).join(Department).join(Faculty).join(University).all()

    result = [
        {
            "enroll_profeId": enrollment.enroll_profeId,
            "id_professor": enrollment.id_professor,
            "NRC": enrollment.NRC,
            "professor": {
                "id_user": enrollment.professor.id_user,
                "nameUser ": enrollment.professor.nameUser ,
                "lastName": enrollment.professor.lastName,
                "email": enrollment.professor.email,
            } if enrollment.professor else None,
            "course": {
                "NRC": enrollment.course.NRC,
                "codeCourse": enrollment.course.codeCourse,
                "nameCourse": enrollment.course.nameCourse,
                "department": {
                    "idDepartment": enrollment.course.idDepartment,
                    "nameDepartment": enrollment.course.department.nameDepartment,
                    "faculty": {
                        "facultyId": enrollment.course.department.faculty.facultyId,
                        "facultyName": enrollment.course.department.faculty.facultyName,
                        "facultyType": enrollment.course.department.faculty.facultyType,
                        "university": {
                            "universityId": enrollment.course.department.faculty.university.universityId,
                            "universityName": enrollment.course.department.faculty.university.universityName,
                            "uniCountry": enrollment.course.department.faculty.university.uniCountry,
                            "uniSede": enrollment.course.department.faculty.university.uniSede,
                        }
                    } if enrollment.course.department.faculty else None
                } if enrollment.course.department else None
            } if enrollment.course else None
        }
        for enrollment in enrollments
    ]
    return jsonify(result), 200