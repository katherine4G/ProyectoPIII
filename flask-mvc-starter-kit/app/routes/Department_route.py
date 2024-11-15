from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers import Department_controller

route_dept = Blueprint('department', __name__, url_prefix='/department')

@route_dept.route('/create', methods=['POST'])
@jwt_required()
def create_department():
    return Department_controller.create()

@route_dept.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_department():
    return Department_controller.delete()

@route_dept.route('/update', methods=['PUT'])
@jwt_required()
def update_department():
    return Department_controller.update()

@route_dept.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_departments():
    return Department_controller.showAll()

@route_dept.route('/showID', methods=['GET'])
@jwt_required()
def show_department_by_id():
    return Department_controller.showID()

@route_dept.route('/showAllWithFaculty', methods=['GET'])
@jwt_required()
def show_all_departments_with_faculty():
    return Department_controller.showAllWithFaculty()

@route_dept.route('/showAllWithFacultyAndUniversity', methods=['GET'])
def show_all_with_faculty_and_university():
    return Department_controller.showAllWithFacultyAndUniversity()

