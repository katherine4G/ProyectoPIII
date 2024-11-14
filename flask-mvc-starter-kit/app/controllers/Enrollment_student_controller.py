from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Enrollment_student import EnrollmentStudent
from app.models.user import User
from app.models.Course import Course
from app.models.Department import Department
from app.models.Faculty import Faculty
from app.models.University import University
from app import db

@jwt_required()
def create():
    data = request.get_json()
    id_student = data.get('id_student')
    NRC = data.get('NRC')

    if not all([id_student, NRC]):
        return jsonify({"Error": -1, "msg": "ID del estudiante y NRC son necesarios"}), 400

    new_enrollment = EnrollmentStudent(id_student=id_student, NRC=NRC)

    try:
        with db.session.begin():
            db.session.add(new_enrollment)

        return jsonify({"code": 1, "msg": "Inscripción creada exitosamente", "enrollment": new_enrollment.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la inscripción: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la inscripción"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    enroll_studentId = data.get("enroll_studentId")

    if not enroll_studentId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        # Busca la inscripción del estudiante por ID
        enrollment = EnrollmentStudent.query.get(enroll_studentId)
        if not enrollment:
            return jsonify({"Error": -1, "msg": "Inscripción no encontrada"}), 404

        # Elimina la inscripción
        db.session.delete(enrollment)
        db.session.commit()  # Guarda los cambios en la base de datos

        return jsonify({"code": 1, "msg": "Inscripción eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()  # Revertir cambios si hay un error
        print(f"Error al eliminar la inscripción: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la inscripción"}), 500

@jwt_required()
def update():
    data = request.get_json()
    enroll_studentId = data.get("enroll_studentId")
    new_id_student = data.get("new_id_student")
    new_NRC = data.get("new_NRC")

    if not enroll_studentId or not new_id_student or not new_NRC:
        return jsonify({"Error": -1, "msg": "ID y todos los nuevos valores son necesarios"}), 400

    try:
        enrollment = EnrollmentStudent.query.get(enroll_studentId)
        if not enrollment:
            return jsonify({"Error": -1, "msg": "Inscripción no encontrada"}), 404

        enrollment.id_student = new_id_student
        enrollment.NRC = new_NRC

        with db.session.begin():
            db.session.merge(enrollment)

        return jsonify({"code": 1, "msg": "Inscripción actualizada exitosamente", "enrollment": enrollment.to_dict()}), 200
    except Exception as e:
        print(f"Error al actualizar la inscripción: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la inscripción"}), 500

@jwt_required()
def showAll():
    try:
        enrollments = EnrollmentStudent.query.all()
        return jsonify([enrollment.to_dict() for enrollment in enrollments]), 200
    except Exception as e:
        print(f"Error al obtener todas las inscripciones de estudiantes: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener inscripciones"}), 500

@jwt_required()
def showID():
    enroll_studentId = request.args.get("enroll_studentId")

    if not enroll_studentId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        enroll_studentId = int(enroll_studentId)  
    except ValueError:
        return jsonify({"Error": -1, "msg": "ID debe ser un número entero"}), 400

    enrollment = EnrollmentStudent.query.get(enroll_studentId)
    if not enrollment:
        return jsonify({"Error": -1, "msg": "Inscripción no encontrada"}), 404

    return jsonify({"code": 1, "msg": "Inscripción encontrada", "enrollment": enrollment.to_dict()}), 200

@jwt_required()
def showAllEnrollmentsWithAllDetails():
    enrollments = db.session.query(EnrollmentStudent).join(User).join(Course).join(Department).join(Faculty).join(University).all()

    result = [
        {
            "enroll_studentId": enrollment.enroll_studentId,
            "id_student": enrollment.id_student,
            "NRC": enrollment.NRC,
            "student": {
                "id_user": enrollment.student.id_user,
                "nameUser ": enrollment.student.nameUser ,
                "lastName": enrollment.student.lastName,
                "email": enrollment.student.email,
            } if enrollment.student else None,
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