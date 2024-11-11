from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers import University_controller

route = Blueprint('university', __name__, url_prefix='/university')

@route.route('/create', methods=['POST'])
@jwt_required()
def create_university():
    return University_controller.create()

@route.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_university():
    return University_controller.delete()

@route.route('/update', methods=['PUT'])
@jwt_required()
def update_university():
    return University_controller.update()

@route.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_universities():
    return University_controller.showAll()

@route.route('/showID', methods=['GET'])
@jwt_required()
def show_university_by_id():
    return University_controller.showID()