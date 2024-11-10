from flask import Blueprint, request, jsonify
from app.controllers import University_controller 

route = Blueprint('university', __name__, url_prefix='/university')

@route.route('/create', methods=['POST'])
def create_university():
    data = request.get_json()  
    success, result = University_controller.create(data) 
    if success:
        return jsonify({"message": "Universidad creada exitosamente", "university": result.to_dict()}), 201
    else:
        return jsonify({"message": result}), 400
    
@route.route('/delete', methods=['DELETE'])
def delete_university():
    return University_controller.delete()

@route.route('/update', methods=['PUT'])
def update_university():
    return University_controller.update()

@route.route('/showAll', methods=['GET'])
def show_all_universities():
    return University_controller.showAll()

@route.route('/showID', methods=['GET'])
def show_university_by_id():
    return University_controller.showID()