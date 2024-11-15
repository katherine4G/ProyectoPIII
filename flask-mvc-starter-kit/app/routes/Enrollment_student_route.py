from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers import Enrollment_student_controller

route_enrollment_student = Blueprint('enrollment_student', __name__, url_prefix='/enrollment_student')

@route_enrollment_student.route('/create', methods=['POST'])
@jwt_required()
def create_enrollment():
    return Enrollment_student_controller.create()

@route_enrollment_student.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_enrollment():
    return Enrollment_student_controller.delete()

@route_enrollment_student.route('/update', methods=['PUT'])
@jwt_required()
def update_enrollment():
    return Enrollment_student_controller.update()

@route_enrollment_student.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_enrollments():
    return Enrollment_student_controller.showAll()

@route_enrollment_student.route('/showID', methods=['GET'])
@jwt_required()
def show_enrollment_by_id():
    return Enrollment_student_controller.showID()

@route_enrollment_student.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_enrollments_with_all_details():
    return Enrollment_student_controller.showAllEnrollmentsWithAllDetails()