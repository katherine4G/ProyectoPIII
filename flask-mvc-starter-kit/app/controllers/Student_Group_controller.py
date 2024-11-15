from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Student_Group import Student_Group
from app.models.user import User
from app.models.Course import Course
from app import db

@jwt_required()
def create():
    data = request.get_json()
    NRC = data.get('NRC')
    groupName = data.get('groupName')
    id_professor = data.get('id_professor')

    if not all([NRC, groupName, id_professor]):
        return jsonify({"Error": -1, "msg": "NRC, groupName y id_professor son necesarios"}), 400

    new_group = Student_Group(NRC=NRC, groupName=groupName, id_professor=id_professor)

    try:
        db.session.add(new_group)
        db.session.commit()
        return jsonify({"code": 1, "msg": "Grupo creado exitosamente", "group": new_group.to_dict()}), 201
    except Exception as e:
        db.session.rollback()
        print(f"Error al crear el grupo: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear el grupo"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    groupId = data.get("groupId")

    if not groupId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        group = Student_Group.query.get(groupId)
        if not group:
            return jsonify({"Error": -1, "msg": "Grupo no encontrado"}), 404

        db.session.delete(group)
        db.session.commit()
        return jsonify({"code": 1, "msg": "Grupo eliminado exitosamente"}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar el grupo: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar el grupo"}), 500

@jwt_required()
def update():
    data = request.get_json()
    groupId = data.get("groupId")
    NRC = data.get("NRC")
    groupName = data.get("groupName")
    id_professor = data.get("id_professor")

    if not all([groupId, NRC, groupName, id_professor]):
        return jsonify({"Error": -1, "msg": "ID, NRC, groupName y id_professor son necesarios"}), 400

    try:
        # Verificar si el id_professor existe
        professor = User.query.get(id_professor)
        if not professor:
            return jsonify({"Error": -1, "msg": "El ID del profesor no existe"}), 400

        # Verificar si el NRC existe
        course = Course.query.get(NRC)
        if not course:
            return jsonify({"Error": -1, "msg": "El NRC no existe"}), 400

        group = Student_Group.query.get(groupId)
        if not group:
            return jsonify({"Error": -1, "msg": "Grupo no encontrado"}), 404

        group.NRC = NRC
        group.groupName = groupName
        group.id_professor = id_professor

        db.session.commit()
        return jsonify({"code": 1, "msg": "Grupo actualizado exitosamente", "group": group.to_dict()}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar el grupo: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar el grupo"}), 500

@ jwt_required()
def showAll():
    try:
        groups = Student_Group.query.all()
        return jsonify([group.to_dict() for group in groups]), 200
    except Exception as e:
        print(f"Error al obtener todos los grupos: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener grupos"}), 500

@jwt_required()
def showID():
    groupId = request.args.get("groupId")

    if not groupId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        groupId = int(groupId)
    except ValueError:
        return jsonify({"Error": -1, "msg": "ID debe ser un número entero"}), 400

    group = Student_Group.query.get(groupId)
    if not group:
        return jsonify({"Error": -1, "msg": "Grupo no encontrado"}), 404

    return jsonify({"code": 1, "msg": "Grupo encontrado", "group": group.to_dict()}), 200

@jwt_required()
def showAllStudentGroupsWithAllDetails():
    student_groups = db.session.query(Student_Group).join(User, Student_Group.id_professor == User.id_user).join(Course, Student_Group.NRC == Course.NRC).all()

    result = [
        {
            "groupId": group.groupId,
            "NRC": group.NRC,
            "groupName": group.groupName,
            "professor": {
                "id_user": group.professor.id_user,
                "nameUser ": group.professor.nameUser ,
                "lastName": group.professor.lastName,
                "email": group.professor.email,
            } if group.professor else None,
            "course": {
                "NRC": group.course.NRC,
                "codeCourse": group.course.codeCourse,
                "nameCourse": group.course.nameCourse,
            } if group.course else None
        }
        for group in student_groups
    ]
    return jsonify(result), 200

