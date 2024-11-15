from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Course_controller import (
    create_course, delete_course, update_course, showID, show_all_courses, showAllWithDepartment, getAllWithDepartmentAndFacultyAndUniversity
)

route_course = Blueprint('course', __name__, url_prefix='/course')

@route_course.route('/create', methods=['POST'])
@jwt_required()
def create_new_course():
    return create_course()

@route_course.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_existing_course():
    return delete_course()

@route_course.route('/update', methods=['PUT'])
@jwt_required()
def update_existing_course():
    return update_course()

@route_course.route('/showID', methods=['GET'])
@jwt_required()
def get_single_course():
    return showID()

@route_course.route('/showAll', methods=['GET'])
@jwt_required()
def get_all_courses_list():
    return show_all_courses()

@route_course.route('/showAllWithDepartment', methods=['GET'])
@jwt_required()
def get_all_courses_with_department():
    return showAllWithDepartment()

@route_course.route('/getAllWithDepartmentAndFacultyAndUniversity', methods=['GET'])
@jwt_required()
def get_all_courses_with_department_faculty_Universities():
    return getAllWithDepartmentAndFacultyAndUniversity()
