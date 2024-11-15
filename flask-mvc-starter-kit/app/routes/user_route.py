from flask import Blueprint
from app.controllers.user_controller import create_user, get_user, get_all_users, login, delete_user

auth_bp = Blueprint('auth', __name__, url_prefix='/auth')

@auth_bp.route('/register', methods=['POST'])
def register():
    return create_user()

@auth_bp.route('/login', methods=['POST'])
def login_user():
    return login()

@auth_bp.route('/user/<string:id_user>', methods=['GET'])
def get_single_user(id_user):
    return get_user(id_user)

@auth_bp.route('/users', methods=['GET'])
def list_users():
    return get_all_users()

@auth_bp.route('/user/delete/<string:id_user>', methods=['DELETE'])
def remove_user(id_user):
    return delete_user(id_user)
