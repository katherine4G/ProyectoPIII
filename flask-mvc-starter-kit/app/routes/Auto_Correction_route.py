from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Auto_Correction_controller import create, delete, update, showAll, showID, showAllWithDetails

auto_correction_route = Blueprint('auto_correction', __name__, url_prefix='/auto_correction')

@auto_correction_route.route('/create', methods=['POST'])
@jwt_required()
def create_auto_correction():
    return create()

@auto_correction_route.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_auto_correction():
    return delete()

@auto_correction_route.route('/update', methods=['PUT'])
@jwt_required()
def update_auto_correction():
    return update()

@auto_correction_route.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_auto_corrections():
    return showAll()

@auto_correction_route.route('/showID', methods=['GET'])
@jwt_required()
def show_auto_correction_by_id():
    return showID()

@auto_correction_route.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_auto_corrections_with_details():
    return showAllWithDetails()