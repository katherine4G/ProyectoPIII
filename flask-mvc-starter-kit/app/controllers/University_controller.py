from flask import request, jsonify
from app.models.University import University
from app import db

TOKEN_PROVISIONAL = "123"

def create(data):
    try:
        universityName = data.get('universityName')
        uniCountry = data.get('uniCountry')
        uniSede = data.get('uniSede')
        uniAdress = data.get('uniAdress')

        if not all([universityName, uniCountry, uniSede, uniAdress]):
            return False, "Todos los campos son necesarios"

        new_university = University(
            universityName=universityName,
            uniCountry=uniCountry,
            uniSede=uniSede,
            uniAdress=uniAdress
        )
        db.session.add(new_university)
        db.session.commit()
        return True, new_university
    except Exception as e:
        print(f"Error al crear la universidad: {e}")
        return False, "Error al crear la universidad"

def delete():
    if request.method == "DELETE":
        data = request.get_json()
        university_id = data.get("universityId")
        token = data.get("token")

        if not university_id or token != TOKEN_PROVISIONAL:
            return jsonify({"Error": -1, "msg": "ID y token necesarios o incorrecto"}), 400

        university = University.query.get(university_id)
        if not university:
            return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

        db.session.delete(university)
        db.session.commit()
        return jsonify({"code": 1, "msg": "Universidad eliminada exitosamente"}), 200

def update():
    if request.method == "PUT":
        data = request.get_json()
        university_id = data.get("universityId")
        university_name = data.get("universityName")
        uni_country = data.get("uniCountry")
        uni_sede = data.get("uniSede")
        uni_address = data.get("uniAdress")
        token = data.get("token")

        if not token or not university_id:
            return jsonify({"Error": -1, "msg": "Token y ID necesarios"}), 400

        university = University.query.get(university_id)
        if not university:
            return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

        university.universityName = university_name or university.universityName
        university.uniCountry = uni_country or university.uniCountry
        university.uniSede = uni_sede or university.uniSede
        university.uniAdress = uni_address or university.uniAdress
        db.session.commit()

        return jsonify({"code": 1, "msg": "Universidad actualizada exitosamente", "university": university.to_dict()}), 200

def showAll():
    universities = University.query.all()
    return jsonify([university.to_dict() for university in universities])

def showID():
    if request.method == "GET":
        university_id = request.args.get("universityId")
        token = request.args.get("token")

        if not token or token != TOKEN_PROVISIONAL or not university_id:
            return jsonify({"Error": -1, "msg": "Token y ID necesarios o incorrectos"}), 400

        university = University.query.get(university_id)
        if not university:
            return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

        return jsonify({"code": 1, "msg": "Universidad encontrada", "university": university.to_dict()}), 200