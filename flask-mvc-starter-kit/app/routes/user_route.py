
# app/routes/user_route.py
from flask import Blueprint
from app.controllers.user_controller import create_user, get_user, get_all_users, login

auth_bp = Blueprint('auth', __name__, url_prefix='/auth')

@auth_bp.route('/register', methods=['POST'])
def register():
    # Esta ruta llama al controlador create_user para registrar un nuevo usuario
    return create_user()

@auth_bp.route('/login', methods=['POST'])
def login_user():
    # Esta ruta llama al controlador login para autenticar a un usuario
    return login()

@auth_bp.route('/user/<string:id_user>', methods=['GET'])  # Usamos id_user como string
def get_single_user(id_user):
    # Esta ruta llama al controlador get_user para obtener un usuario por su id
    return get_user(id_user)

@auth_bp.route('/users', methods=['GET'])
def list_users():
    # Esta ruta llama al controlador get_all_users para listar todos los usuarios
    return get_all_users()
