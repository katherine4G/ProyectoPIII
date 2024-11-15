from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Submission import Submission
from app.models.Assignment import Assignment
from app.models.user import User
from app import db

@jwt_required()
def create():
    data = request.get_json()
    assignmentId = data.get('assignmentId')
    id_student = data.get('id_student')
    file_path = data.get('file_path')
    grade = data.get('grade')
    comments = data.get('comments')

    if not all([assignmentId, id_student]):
        return jsonify({"Error": -1, "msg": "assignmentId y id_student son necesarios"}), 400

    new_submission = Submission(
        assignmentId=assignmentId,
        id_student=id_student,
        file_path=file_path,
        grade=grade,
        comments=comments
    )

    try:
        with db.session.begin():
            db.session.add(new_submission)

        return jsonify({"code": 1, "msg": "Entrega creada exitosamente", "submission": new_submission.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la entrega: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la entrega"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    submissionId = data.get("submissionId")

    if not submissionId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        submission = Submission.query.get(submissionId)
        if not submission:
            return jsonify({"Error": -1, "msg": "Entrega no encontrada"}), 404

        db.session.delete(submission)
        db.session.commit()

        return jsonify({"code": 1, "msg": "Entrega eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar la entrega: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la entrega"}), 500

@jwt_required()
def update():
    data = request.get_json()
    submissionId = data.get("submissionId")
    grade = data.get("grade")
    comments = data.get("comments")
    taskReviewed = data.get("taskReviewed")
    taskSubmitted = data.get("taskSubmitted")

    if not submissionId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        submission = Submission.query.get(submissionId)
        if not submission:
            return jsonify({"Error": -1, "msg": "Entrega no encontrada"}), 404

        if grade is not None:
            submission.grade = grade
        if comments is not None:
            submission.comments = comments
        if taskReviewed is not None:
            submission.taskReviewed = taskReviewed
        if taskSubmitted is not None:
            submission.taskSubmitted = taskSubmitted

        db.session.commit()

        return jsonify({"code": 1, "msg": "Entrega actualizada exitosamente", "submission": submission.to_dict()}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar la entrega: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la entrega"}), 500

@jwt_required()
def showAll():
    try:
        submissions = Submission.query.all()
        return jsonify([submission.to_dict() for submission in submissions]), 200
    except Exception as e:
        print(f"Error al obtener todas las entregas: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener entregas"}), 500

@jwt_required()
def showID():
    submissionId = request.args.get("submissionId")

    if not submissionId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        submissionId = int(submissionId)
    except ValueError:
        return jsonify({"Error": -1, "msg": "ID debe ser un número entero"}), 400

    submission = Submission.query.get(submissionId)
    if not submission:
        return jsonify({"Error": -1, "msg": "Entrega no encontrada"}), 404

    return jsonify({"code": 1, "msg": "Entrega encontrada", "submission": submission.to_dict()}), 200

@jwt_required()
def showAllSubmissionsWithDetails():
    submissions = db.session.query(Submission).join(Assignment, Submission.assignmentId == Assignment.assignmentId).join(User, Submission.id_student == User.id_user).all()

    result = [
        {
            "submissionId": submission.submissionId,
            "assignmentId": submission.assignmentId,
            "id_student": submission.id_student,
            "submission_date": submission.submission_date,
            "file_path": submission.file_path,
            "grade": str(submission.grade),  # Convertir a string para JSON
            "comments": submission.comments,
            "taskReviewed": submission.taskReviewed,
            "taskSubmitted": submission.taskSubmitted,
            "assignment": {
                "assignmentId": submission.assignment.assignmentId,
                "NRC": submission.assignment.NRC,
                "title": submission.assignment.title,
                "description": submission.assignment.description,
                "due_date": submission.assignment.due_date,
                "file_path": submission.assignment.file_path,
                "groupId": submission.assignment.groupId,
            } if submission.assignment else None,
            "student": {
                "id_user": submission.student.id_user,
                "nameUser ": submission.student.nameUser ,
                "lastName": submission.student.lastName,
                "email": submission.student.email,
            } if submission.student else None
        }
        for submission in submissions
    ]
    return jsonify(result), 200