#controllers/Faculty_controller.py
from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Faculty import Faculty
from app.models.University import University
from app import db

@jwt_required()
def create():
    data = request.get_json()
    try:
        facultyName = data.get('facultyName')
        facultyType = data.get('facultyType')
        universityId = data.get('universityId')

        if not all([facultyName, facultyType, universityId]):
            return jsonify({"Error": -1, "msg": "Todos los campos son necesarios"}), 400

        new_faculty = Faculty(
            facultyName=facultyName,
            facultyType=facultyType,
            universityId=universityId
        )
        db.session.add(new_faculty)
        db.session.commit()
        return jsonify({"code": 1, "msg": "Facultad creada exitosamente", "faculty": new_faculty.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la facultad: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la facultad"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    faculty_id = data.get("facultyId")

    if not faculty_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    faculty = Faculty.query.get(faculty_id)
    if not faculty:
        return jsonify({"Error": -1, "msg": "Facultad no encontrada"}), 404

    db.session.delete(faculty)
    db.session.commit()
    return jsonify({"code": 1, "msg": "Facultad eliminada exitosamente"}), 200

@jwt_required()
def update():
    data = request.get_json()
    faculty_id = data.get("facultyId")
    faculty_name = data.get("facultyName")
    faculty_type = data.get("facultyType")
    university_id = data.get("universityId")

    if not faculty_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    faculty = Faculty.query.get(faculty_id)
    if not faculty:
        return jsonify({"Error": -1, "msg": "Facultad no encontrada"}), 404

    faculty.facultyName = faculty_name or faculty.facultyName
    faculty.facultyType = faculty_type or faculty.facultyType
    faculty.universityId = university_id or faculty.universityId
    db.session.commit()

    return jsonify({"code": 1, "msg": "Facultad actualizada exitosamente", "faculty": faculty.to_dict()}), 200

@jwt_required()
def showAll():
    faculties = Faculty.query.all()
    return jsonify([faculty.to_dict() for faculty in faculties]), 200

@jwt_required()
def showID():
    faculty_id = request.args.get("facultyId")  

    if not faculty_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    faculty = Faculty.query.get(faculty_id)
    if not faculty:
        return jsonify({"Error": -1, "msg": "Facultad no encontrada"}), 404

    return jsonify({"code": 1, "msg": "Facultad encontrada", "faculty": faculty.to_dict()}), 200

@jwt_required()
def showAllWithUniversity():
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
