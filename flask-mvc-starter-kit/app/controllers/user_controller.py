#user _controller.py:
from flask import request, jsonify
from werkzeug.security import check_password_hash, generate_password_hash
from flask_jwt_extended import create_access_token
from app.models.user import User
from app import db

# Obtener usuario por ID
def get_user(id_user):
    user = User.query.get(id_user) 
    if not user:
        return jsonify({"msg": "Usuario no encontrado"}), 404

    return jsonify({
        "id_user": user.id_user,
        "nameUser": user.nameUser,
        "lastName": user.lastName,
        "email": user.email,
        "role_id": user.role_id
    }), 200

# Obtener todos los usuarios
def get_all_users():
    users = User.query.all()
    return jsonify([{
        "id_user": user.id_user,
        "nameUser": user.nameUser,
        "lastName": user.lastName,
        "email": user.email,
        "role_id": user.role_id
    } for user in users]), 200

# Crear usuario
def create_user():
    data = request.get_json()
    id_user = data.get("id_user")
    nameUser = data.get("nameUser")
    lastName = data.get("lastName")
    email = data.get("email")
    password = data.get("password")
    role_id = data.get("role_id")

    # Verifica que todos los campos estén presentes
    if not id_user or not nameUser or not lastName or not email or not password or not role_id:
        return jsonify({"msg": "Todos los campos son requeridos"}), 400

    # Verifica si el id_user o el email ya existe en la base de datos
    existing_user = User.query.filter((User.id_user == id_user) | (User.email == email)).first()  # Revisa id_user y email
    if existing_user:
        return jsonify({"msg": "El ID de usuario o el correo ya están en uso"}), 400

    # Hash de la contraseña
    hashed_password = generate_password_hash(password)

    # Crea el nuevo usuario
    new_user = User(
        id_user=id_user,
        nameUser=nameUser,
        lastName=lastName,
        email=email,
        password=hashed_password,
        role_id=role_id
    )
    db.session.add(new_user)
    db.session.commit()

    return jsonify({"msg": "Usuario creado exitosamente", "user": new_user.nameUser}), 201

def login():
    try:
        data = request.get_json()
        id_user = data.get("id_user")
        password = data.get("password")

        if not id_user or not password:
            return jsonify({"msg": "Usuario y contraseña son requeridos"}), 400

        # Busca el usuario en la base de datos
        user = User.query.filter_by(id_user=id_user).first()  # Verifica por 'id_user'
        
        # Si el usuario no existe o la contraseña no coincide
        if not user or not check_password_hash(user.password, password):
            return jsonify({"msg": "Credenciales inválidas"}), 401

        # Genera el token de acceso
        access_token = create_access_token(identity=user.id_user)

        # Devuelve el token y el role_id
        return jsonify(access_token=access_token, role_id=user.role_id), 200

    except Exception as e:
        print(f"Error en el servidor: {e}")
        return jsonify({"msg": "Error en el servidor"}), 500

def delete_user(id_user):
    user = User.query.get(id_user)
    if not user:
        return jsonify({"msg": "Usuario no encontrado"}), 404

    db.session.delete(user)
    db.session.commit()
    return jsonify({"msg": "Usuario eliminado exitosamente"}), 200