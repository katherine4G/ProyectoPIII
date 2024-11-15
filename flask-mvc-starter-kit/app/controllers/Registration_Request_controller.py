from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Registration_Request import RegistrationRequest
from app.models.user import User
from app import db

@jwt_required()
def create():
    data = request.get_json()
    id_user = data.get('id_user')
    request_type = data.get('request_type')

    if not all([id_user, request_type]):
        return jsonify({"Error": -1, "msg": "id_user y request_type son necesarios"}), 400

    new_request = RegistrationRequest(
        id_user=id_user,
        request_type=request_type
    )

    try:
        with db.session.begin():
            db.session.add(new_request)

        return jsonify({"code": 1, "msg": "Solicitud creada exitosamente", "request": new_request.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la solicitud: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la solicitud"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    requestId = data.get("requestId")

    if not requestId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        request = RegistrationRequest.query.get(requestId)
        if not request:
            return jsonify({"Error": -1, "msg": "Solicitud no encontrada"}), 404

        db.session.delete(request)
        db.session.commit()

        return jsonify({"code": 1, "msg": "Solicitud eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar la solicitud: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la solicitud"}), 500

@jwt_required()
def update():
    update_data = request.get_json()
    requestId = update_data.get("requestId")
    status = update_data.get("status")

    if not requestId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    # Para validar que el status sea uno de los valores permitidos
    ALLOWED_STATUS = ['Pendiente', 'Aceptada', 'Denegada']
    if status is not None and status not in ALLOWED_STATUS:
        return jsonify({
            "Error": -1, 
            "msg": f"Estado inválido. Valores permitidos: {ALLOWED_STATUS}"
        }), 400

    try:
        registration_request = RegistrationRequest.query.get(requestId)
        if not registration_request:
            return jsonify({"Error": -1, "msg": "Solicitud no encontrada"}), 404

        if status is not None:
            registration_request.status = status

        db.session.commit()

        return jsonify({
            "code": 1, 
            "msg": "Solicitud actualizada exitosamente", 
            "request": registration_request.to_dict()
        }), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar la solicitud: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la solicitud"}), 500
    
@jwt_required()
def showAll():
    try:
        requests = RegistrationRequest.query.all()
        return jsonify([request.to_dict() for request in requests]), 200
    except Exception as e:
        print(f"Error al obtener todas las solicitudes: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener solicitudes"}), 500

@jwt_required()
def showID():
    requestId = request.args.get("requestId")

    if not requestId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        registration_request = RegistrationRequest.query.get(requestId)
        if not registration_request:
            return jsonify({"Error": -1, "msg": "Solicitud no encontrada"}), 404

        return jsonify({
            "code": 1,
            "msg": "Solicitud encontrada",
            "request": registration_request.to_dict()
        }), 200
    except Exception as e:
        print(f"Error al obtener la solicitud: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener la solicitud"}), 500

@jwt_required()
def showAllRegistrationWithDetails():
    requests = db.session.query(RegistrationRequest).join(User, RegistrationRequest.id_user == User.id_user).all()

    result = [
        {
            "requestId": request.requestId,
            "id_user": request.id_user,
            "request_type": request.request_type,
            "status": request.status,
            "requested_at": request.requested_at,
            "user": {
                "id_user": request.user.id_user,
                "nameUser ": request.user.nameUser ,
                "lastName": request.user.lastName,
                "email": request.user.email,
            } if request.user else None
        }
        for request in requests
    ]
    return jsonify(result), 200