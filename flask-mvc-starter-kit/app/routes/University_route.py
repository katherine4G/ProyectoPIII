# app/routes/University_route.py

from flask import Blueprint, request, jsonify
from app.controllers import University_controller 

route = Blueprint('university', __name__, url_prefix='/university')

@route.route('/create', methods=['POST'])
def create():
    data = request.get_json()  
    success, result = University_controller.create(data) 
    if success:
        return jsonify({"message": "Universidad creada exitosamente", "university": result.to_dict()}), 201
    else:
        return jsonify({"message": result}), 400
    

@route.route('/showAll', methods=['GET'])
def show_all_universities():
    return University_controller.showAll()

@route.route('/show/<int:university_id>', methods=['GET'])
def show_university_by_id(university_id):
    success, result = University_controller.showID(university_id)
    if success:
        return jsonify({"university": result.to_dict()}), 200
    else:
        return jsonify({"message": result}), 404

@route.route('/delete/<int:university_id>', methods=['DELETE'])
def delete_university(university_id):
    success, result = University_controller.delete(university_id)
    if success:
        return jsonify({"message": result}), 200
    else:
        return jsonify({"message": result}), 404

@route.route('/update/<int:university_id>', methods=['PUT'])
def update_university(university_id):
    data = request.get_json()
    success, result = University_controller.update(university_id, data)
    if success:
        return jsonify({"message": result}), 200
    else:
        return jsonify({"message": result}), 404
