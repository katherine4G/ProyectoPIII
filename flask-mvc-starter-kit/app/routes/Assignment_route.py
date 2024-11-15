from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Assignment_controller import create, delete, update, showAll, showID,showAllAssignmentsWithDetails

assignment_route = Blueprint('assignment', __name__, url_prefix='/assignment')

@assignment_route.route('/create', methods=['POST'])
@jwt_required()
def create_assignment():
    return create()

@assignment_route.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_assignment():
    return delete()

@assignment_route.route('/update', methods=['PUT'])
@jwt_required()
def update_assignment():
    return update()

@assignment_route.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_assignments():
    return showAll()

@assignment_route.route('/showID', methods=['GET'])
@jwt_required()
def show_assignment_by_id():
    return showID()

@assignment_route.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_assignments_with_details():
    return showAllAssignmentsWithDetails()