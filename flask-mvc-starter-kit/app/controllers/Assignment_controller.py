from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Assignment import Assignment
from app.models.user import User
from app.models.Course import Course
from app.models.Student_Group import Student_Group
from app import db

@jwt_required()
def create():
    data = request.get_json()
    NRC = data.get('NRC')
    id_professor = data.get('id_professor')
    title = data.get('title')
    description = data.get('description')
    due_date = data.get('due_date')
    file_path = data.get('file_path')
    groupId = data.get('groupId')

    if not all([NRC, id_professor, title]):
        return jsonify({"Error": -1, "msg": "NRC, ID del profesor y título son necesarios"}), 400

    new_assignment = Assignment(
        NRC=NRC,
        id_professor=id_professor,
        title=title,
        description=description,
        due_date=due_date,
        file_path=file_path,
        groupId=groupId
    )

    try:
        with db.session.begin():
            db.session.add(new_assignment)

        return jsonify({"code": 1, "msg": "Asignación creada exitosamente", "assignment": new_assignment.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la asignación: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la asignación"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    assignmentId = data.get("assignmentId")

    if not assignmentId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        assignment = Assignment.query.get(assignmentId)
        if not assignment:
            return jsonify({"Error": -1, "msg": "Asignación no encontrada"}), 404

        db.session.delete(assignment)
        db.session.commit()

        return jsonify({"code": 1, "msg": "Asignación eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar la asignación: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la asignación"}), 500
    
@jwt_required()
def update():
    data = request.get_json()
    assignmentId = data.get("assignmentId")
    new_NRC = data.get("new_NRC")
    new_id_professor = data.get("new_id_professor")
    new_title = data.get("new_title")
    new_description = data.get("new_description")
    new_due_date = data.get("new_due_date")
    new_file_path = data.get("new_file_path")
    new_groupId = data.get("new_groupId")

    if not assignmentId or not new_NRC or not new_id_professor or not new_title:
        return jsonify({"Error": -1, "msg": "ID y todos los nuevos valores son necesarios"}), 400

    try:
        assignment = Assignment.query.get(assignmentId)
        if not assignment:
            return jsonify({"Error": -1, "msg": "Asignación no encontrada"}), 404

        assignment.NRC = new_NRC
        assignment.id_professor = new_id_professor
        assignment.title = new_title
        assignment.description = new_description
        assignment.due_date = new_due_date
        assignment.file_path = new_file_path
        assignment.groupId = new_groupId

        db.session.commit()

        return jsonify({"code": 1, "msg": "Asignación actualizada exitosamente", "assignment": assignment.to_dict()}), 200
    except Exception as e:
        db.session.rollback()  # Si ocurre un error, hacemos rollback
        print(f"Error al actualizar la asignación: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la asignación"}), 500

@jwt_required()
def showAll():
    try:
        assignments = Assignment.query.all()
        return jsonify([assignment.to_dict() for assignment in assignments]), 200
    except Exception as e:
        print(f"Error al obtener todas las asignaciones: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener asignaciones"}), 500

@jwt_required()
def showID():
    assignmentId = request.args.get("assignmentId")

    if not assignmentId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        assignmentId = int(assignmentId)
    except ValueError:
        return jsonify({"Error": -1, "msg": "ID debe ser un número entero"}), 400

    assignment = Assignment.query.get(assignmentId)
    if not assignment:
        return jsonify({"Error": -1, "msg": "Asignación no encontrada"}), 404

    return jsonify({"code": 1, "msg": "Asignación encontrada", "assignment": assignment.to_dict()}), 200

@jwt_required()
def showAllAssignmentsWithDetails():
    assignments = db.session.query(Assignment).join(User, Assignment.id_professor == User.id_user).join(Course, Assignment.NRC == Course.NRC).join(Student_Group, Assignment.groupId == Student_Group.groupId).all()

    result = [
        {
            "assignmentId": assignment.assignmentId,
            "NRC": assignment.NRC,
            "title": assignment.title,
            "description": assignment.description,
            "due_date": assignment.due_date,
            "file_path": assignment.file_path,
            "groupId": assignment.groupId,
            "professor": {
                "id_user": assignment.professor.id_user,
                "nameUser ": assignment.professor.nameUser ,
                "lastName": assignment.professor.lastName,
                "email": assignment.professor.email,
            } if assignment.professor else None,
            "course": {
                "NRC": assignment.course.NRC,
                "codeCourse": assignment.course.codeCourse,
                "nameCourse": assignment.course.nameCourse,
            } if assignment.course else None,
            "group": {
                "groupId": assignment.group.groupId,
                "groupName": assignment.group.groupName,
            } if assignment.group else None
        }
        for assignment in assignments
    ]
    return jsonify(result), 200