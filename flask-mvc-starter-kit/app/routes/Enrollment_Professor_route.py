from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers import Enrollment_Professor_controller

route_enrollment_professor = Blueprint('enrollment_professor', __name__, url_prefix='/enrollment_professor')

@route_enrollment_professor.route('/create', methods=['POST'])
@jwt_required()
def create_enrollment():
    return Enrollment_Professor_controller.create()

@route_enrollment_professor .route('/delete', methods=['DELETE'])
@jwt_required()
def delete_enrollment():
    return Enrollment_Professor_controller.delete()

@route_enrollment_professor.route('/update', methods=['PUT'])
@jwt_required()
def update_enrollment():
    return Enrollment_Professor_controller.update()

@route_enrollment_professor.route('/show_all', methods=['GET'])
@jwt_required()
def show_all_enrollments():
    return Enrollment_Professor_controller.showAll()

@route_enrollment_professor.route('/show', methods=['GET'])
@jwt_required()
def show_enrollment():
    return Enrollment_Professor_controller.showID()

@route_enrollment_professor.route('/show_all_professor_enrollments_with_details', methods=['GET'])
@jwt_required()
def show_all_enrollments_professors_with_all_details():
    return Enrollment_Professor_controller.showAllEnrollmentsProfessorsWithAllDetails()