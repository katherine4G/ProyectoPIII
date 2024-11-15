from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Registration_Request_controller import create, delete, update, showAll, showID, showAllRegistrationWithDetails

registration_request_route = Blueprint('registration_request', __name__, url_prefix='/registration_request')

@registration_request_route.route('/create', methods=['POST'])
@jwt_required()
def create_registration_request():
    return create()

@registration_request_route.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_registration_request():
    return delete()

@registration_request_route.route('/update', methods=['PUT'])
@jwt_required()
def update_registration_request():
    return update()

@registration_request_route.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_registration_requests():
    return showAll()

@registration_request_route.route('/showID', methods=['GET'])
@jwt_required()
def show_registration_request_by_id():
    return showID()

@registration_request_route.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_registration_requests_with_details():
    return showAllRegistrationWithDetails()