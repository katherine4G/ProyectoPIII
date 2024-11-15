from flask import Blueprint
from flask_jwt_extended import jwt_required
from app.controllers.Manual_Review_controller import create, delete, update, showAll, showID, showAllWithDetails

manual_review_route = Blueprint('manual_review', __name__, url_prefix='/manual_review')

@manual_review_route.route('/create', methods=['POST'])
@jwt_required()
def create_manual_review():
    return create()

@manual_review_route.route('/delete', methods=['DELETE'])
@jwt_required()
def delete_manual_review():
    return delete()

@manual_review_route.route('/update', methods=['PUT'])
@jwt_required()
def update_manual_review():
    return update()

@manual_review_route.route('/showAll', methods=['GET'])
@jwt_required()
def show_all_manual_reviews():
    return showAll()

@manual_review_route.route('/showID', methods=['GET'])
@jwt_required()
def show_manual_review_by_id():
    return showID()

@manual_review_route.route('/showAllWithDetails', methods=['GET'])
@jwt_required()
def show_all_manual_reviews_with_details():
    return showAllWithDetails()