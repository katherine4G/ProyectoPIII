from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Submission_controller import create, delete, update, showAll, showID, showAllSubmissionsWithDetails

submission_route = Blueprint('submission', __name__, url_prefix='/submission')

@submission_route.route('/create', methods=['POST'])
@jwt_required()
def create_submission():
    return create()

@submission_route.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_submission():
    return delete()

@submission_route.route('/update', methods=['PUT'])
@jwt_required()
def update_submission():
    return update()

@submission_route.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_submissions():
    return showAll()

@submission_route.route('/showID', methods=['GET'])
@jwt_required()
def show_submission_by_id():
    return showID()

@submission_route.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_submissions_with_details():
    return showAllSubmissionsWithDetails()