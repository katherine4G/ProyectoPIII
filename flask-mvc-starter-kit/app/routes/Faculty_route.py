from urllib import request
from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers import Faculty_controller

route_facu = Blueprint('faculty', __name__, url_prefix='/faculty')

@route_facu.route('/create', methods=['POST'])
@jwt_required()
def create_faculty():
    return Faculty_controller.create()

@route_facu.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_faculty():
    return Faculty_controller.delete()

@route_facu.route('/update', methods=['PUT'])
@jwt_required()
def update_faculty():
    return Faculty_controller.update()

@route_facu.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_faculties():
    return Faculty_controller.showAll()

@route_facu.route('/showID', methods=['GET'])
@jwt_required()
def show_faculty_by_id():
    university_id = request.args.get('universityId')  # Obtiene el parámetro de la URL
    return Faculty_controller.showID(university_id)

@route_facu.route('/showAllWithUniversity', methods=['GET'])
@jwt_required()
def show_all_faculties_with_university():
    return Faculty_controller.showAllWithUniversity()