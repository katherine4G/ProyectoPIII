from flask import request, jsonify
from app.models.University import University
from app import db

TOKEN_PROVISIONAL = "123"  # Token provisional para pruebas

def create(data):
    try:
        # Obtener los datos del JSON
        universityName = data.get('universityName')
        uniCountry = data.get('uniCountry')
        uniSede = data.get('uniSede')
        uniAdress = data.get('uniAdress')

        # Validar si los campos necesarios están presentes
        if not all([universityName, uniCountry, uniSede, uniAdress]):
            return False, "Todos los campos son necesarios"

        # Crear la universidad
        success, new_university = University.create(universityName, uniCountry, uniSede, uniAdress)
        if success:
            return True, new_university
        else:
            return False, new_university
    except Exception as e:
        print(f"Error al crear la universidad: {e}")
        return False, "Error al crear la universidad"

def showAll():
    universities = University.query.all()  # Recupera todas las universidades de la base de datos
    return jsonify([university.to_dict() for university in universities])

def delete(university_id):
    try:
        university = University.query.get(university_id)
        if not university:
            return False, "Universidad no encontrada"
        
        db.session.delete(university)
        db.session.commit()
        return True, "Universidad eliminada correctamente"
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar la universidad: {e}")
        return False, "Error al eliminar la universidad"

def update(university_id, data):
    try:
        university = University.query.get(university_id)
        if not university:
            return False, "Universidad no encontrada"
        
        # Actualizar los campos con los nuevos valores del JSON
        university.universityName = data.get('universityName', university.universityName)
        university.uniCountry = data.get('uniCountry', university.uniCountry)
        university.uniSede = data.get('uniSede', university.uniSede)
        university.uniAdress = data.get('uniAdress', university.uniAdress)

        db.session.commit()
        return True, "Universidad actualizada correctamente"
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar la universidad: {e}")
        return False, "Error al actualizar la universidad"

def showID(university_id):
    university = University.query.get(university_id)
    if not university:
        return False, "Universidad no encontrada"
    
    return True, university
def selectID(cls, university_id):
        return University.query.get(university_id)

# def update():
#     if request.method == "PUT":
#         university_id = request.form.get("universityId")
#         university_name = request.form.get("universityName")
#         uni_country = request.form.get("uniCountry")
#         uni_sede = request.form.get("uniSede")
#         uni_address = request.form.get("uniAdress")
#         token = request.form.get("token")

#         if not token or not university_id:
#             return jsonify({"Error": -1, "msg": "Token y ID necesarios"})

#         university = University.query.get(university_id)
#         if not university:
#             return jsonify({"Error": -1, "msg": "Universidad no encontrada"})

#         university.universityName = university_name or university.universityName
#  
#        university.uniCountry = uni_country or university.uniCountry
#         university.uniSede = uni_sede or university.uniSede
#         university.uniAdress = uni_address or university.uniAdress
#         db.session.commit()

#         return jsonify({"code": 1, "msg": "Universidad actualizada exitosamente", "university": university.universityName})
 #####################################
# def show_by_id():
#     if request.method == "GET":
#         university_id = request.args.get("universityId")
#         token = request.args.get("token")

#         if not token or token != TOKEN_PROVISIONAL or not university_id:
#             return jsonify({"Error": -1, "msg": "Token y ID necesarios o incorrectos"})

#         university = University.query.get(university_id)
#         if not university:
#             return jsonify({"Error": -1, "msg": "Universidad no encontrada"})

#         return jsonify({"code": 1, "msg": "Universidad encontrada", "university": university.to_dict()})