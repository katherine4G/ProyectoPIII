# app/routes/user_route.py
from flask import Blueprint
from app.controllers.user_controller import create_user, get_user, get_all_users, login

auth_bp = Blueprint('auth', __name__, url_prefix='/auth')

@auth_bp.route('/register', methods=['POST'])
def register():
    return create_user()

@auth_bp.route('/login', methods=['POST'])
def login_user():
    return login()

@auth_bp.route('/user/<int:user_id>', methods=['GET'])
def get_single_user(user_id):
    return get_user(user_id)

@auth_bp.route('/users', methods=['GET'])
def list_users():
    return get_all_users()
