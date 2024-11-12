#controller/University_controller.py
from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.University import University
from app import db

@jwt_required()
def create():
    data = request.get_json()
    try:
        universityName = data.get('universityName')
        uniCountry = data.get('uniCountry')
        uniSede = data.get('uniSede')
        uniAdress = data.get('uniAdress')

        if not all([universityName, uniCountry, uniSede, uniAdress]):
            return jsonify({"Error": -1, "msg": "Todos los campos son necesarios"}), 400

        new_university = University(
            universityName=universityName,
            uniCountry=uniCountry,
            uniSede=uniSede,
            uniAdress=uniAdress
        )
        db.session.add(new_university)
        db.session.commit()
        return jsonify({"code": 1, "msg": "Universidad creada exitosamente", "university": new_university.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la universidad: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la universidad"}), 500
    
@jwt_required()
def delete():
    data = request.get_json()
    university_id = data.get("universityId")

    if not university_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    university = University.query.get(university_id)
    if not university:
        return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

    db.session.delete(university)
    db.session.commit()
    return jsonify({"code": 1, "msg": "Universidad eliminada exitosamente"}), 200

@jwt_required()
def update():
    data = request.get_json()
    university_id = data.get("universityId")
    university_name = data.get("universityName")
    uni_country = data.get("uniCountry")
    uni_sede = data.get("uniSede")
    uni_address = data.get("uniAdress")

    if not university_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    university = University.query.get(university_id)
    if not university:
        return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

    university.universityName = university_name or university.universityName
    university.uniCountry = uni_country or university.uniCountry
    university.uniSede = uni_sede or university.uniSede
    university.uniAdress = uni_address or university.uniAdress
    db.session.commit()

    return jsonify({"code": 1, "msg": "Universidad actualizada exitosamente", "university": university.to_dict()}), 200

@jwt_required()
def showAll():
    universities = University.query.all()
    return jsonify([university.to_dict() for university in universities]), 200

@jwt_required()
def showID():
    university_id = request.args.get("universityId")

    if not university_id:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    university = University.query.get(university_id)
    if not university:
        return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

    return jsonify({"code": 1, "msg": "Universidad encontrada", "university": university.to_dict()}), 200