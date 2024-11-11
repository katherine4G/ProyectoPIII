from flask import request, jsonify
from werkzeug.security import check_password_hash, generate_password_hash
from flask_jwt_extended import create_access_token
from app.models.user import User
from app import db

# Obtener usuario por ID
def get_user(user_id):
    user = User.query.get(user_id)
    if not user:
        return jsonify({"msg": "Usuario no encontrado"}), 404

    return jsonify({"id": user.id, "username": user.username}), 200

# Obtener todos los usuarios
def get_all_users():
    users = User.query.all()
    return jsonify([{"id": user.id, "username": user.username} for user in users]), 200


# Crear usuario
def create_user():
    data = request.get_json()
    username = data.get("username")
    password = data.get("password")

    if not username or not password:
        return jsonify({"msg": "Usuario y contraseña son requeridos"}), 400

    # Verifica si el usuario ya existe
    existing_user = User.query.filter_by(username=username).first()
    if existing_user:
        return jsonify({"msg": "El nombre de usuario ya está en uso"}), 400

    # Hash de la contraseña
    hashed_password = generate_password_hash(password)

    # Crea el nuevo usuario
    new_user = User(username=username, password=hashed_password)
    db.session.add(new_user)
    db.session.commit()

    return jsonify({"msg": "Usuario creado exitosamente", "user": new_user.username}), 201

# Inicio de sesión
def login():
    try:
        data = request.get_json()
        username = data.get("username")
        password = data.get("password")

        if not username or not password:
            return jsonify({"msg": "Usuario y contraseña son requeridos"}), 400

        # Busca el usuario en la base de datos
        user = User.query.filter_by(username=username).first()
        if not user or not check_password_hash(user.password, password):
            return jsonify({"msg": "Credenciales inválidas"}), 401

        # Genera el token de acceso
        access_token = create_access_token(identity=user.id)
        return jsonify(access_token=access_token), 200

    except Exception as e:
        print(f"Error en el servidor: {e}")
        return jsonify({"msg": "Error en el servidor"}), 500
