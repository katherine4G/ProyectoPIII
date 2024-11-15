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

        with db.session.begin():  # Manejo automático de la sesión
            db.session.add(new_university)

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

    try:
        with db.session.begin(): 
            university = db.session.merge(University.query.get(university_id))
            if not university:
                return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

            db.session.delete(university)

        return jsonify({"code": 1, "msg": "Universidad eliminada exitosamente"}), 200

    except Exception as e:
        print(f"Error al eliminar la universidad: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la universidad"}), 500

@jwt_required()
def update():
    data = request.get_json()
    university_id = data.get("universityId")
    new_name = data.get("newName")
    new_country = data.get("newCountry")
    new_sede = data.get("newSede")
    new_address = data.get("newAddress")

    if not university_id or not new_name or not new_country or not new_sede or not new_address:
        return jsonify({"Error": -1, "msg": "ID y todos los nuevos valores son necesarios"}), 400

    try:
        with db.session.begin(): 
            university = University.query.get(university_id)
            if not university:
                return jsonify({"Error": -1, "msg": "Universidad no encontrada"}), 404

            university.universityName = new_name
            university.uniCountry = new_country
            university.uniSede = new_sede
            university.uniAdress = new_address

            db.session.merge(university)  # Sincroniza con la sesión

        updated_university = University.query.get(university_id)
        return jsonify({"code": 1, "msg": "Universidad actualizada exitosamente", "university": updated_university.to_dict()}), 200

    except Exception as e:
        print(f"Error al actualizar la universidad: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la universidad"}), 500

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


@jwt_required()
def showPaginated():
    try:
        page = request.args.get("page", 1)
        per_page = request.args.get("per_page", 10) #10 elementos

        paginated_universities = University.query.paginate(page=page, per_page=per_page, error_out=False)

        universities = [university.to_dict() for university in paginated_universities.items] 

        response = {
            "page": paginated_universities.page,
            "total_pages": paginated_universities.pages,
            "total_items": paginated_universities.total,
            "universities": universities
        }
        return jsonify(response), 200

    except Exception as e:
        print(f"Error al obtener universidades paginadas: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener universidades"}), 500

  
@jwt_required()  
def showPage():
    try:
        # Parámetros de la página y límite de elementos
        page = int(request.args.get('page', 1))  # Página actual, por defecto la 1
        per_page = int(request.args.get('per_page', 10))  # Elementos por página, por defecto 10
        universities_paged = University.query.paginate(page=page, per_page=per_page, error_out=False)
        
        # Verificar si hay resultados y definir 'universities'
        if universities_paged.items:
            universities = [university.to_dict() for university in universities_paged.items]
            return jsonify({
                "code": 1,
                "msg": "Universities found",
                "universities": universities,
                "total_pages": universities_paged.pages,
                "current_page": universities_paged.page
            })
        else:
            return jsonify({"Error": -1, "msg": "No universities found"})

    except Exception as e:
        return jsonify({"Error": -1, "msg": f"An error occurred: {e}"})