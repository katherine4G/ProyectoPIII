from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Student_Group_controller import create, delete, update, showAll, showID, showAllStudentGroupsWithAllDetails

student_group_bp = Blueprint('student_group', __name__, url_prefix='/student_group')

@student_group_bp.route('/create', methods=['POST'])
@jwt_required()
def create_student_group():
    return create()

@student_group_bp.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_student_group():
    return delete()

@student_group_bp.route('/update', methods=['PUT'])
@jwt_required()
def update_student_group():
    return update()

@student_group_bp.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_student_groups():
    return showAll()

@student_group_bp.route('/showID', methods=['GET'])
@jwt_required()
def show_student_group_by_id():
    return showID()

@student_group_bp.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_student_groups_with_details():
    return showAllStudentGroupsWithAllDetails()