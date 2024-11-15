from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Manual_Review import ManualReview
from app.models.Submission import Submission  
from app.models.user import User 
from app import db

@jwt_required()
def create():
    data = request.get_json()
    submissionId = data.get('submissionId')
    id_professor = data.get('id_professor')
    grade = data.get('grade')
    comments = data.get('comments')

    if not all([submissionId, id_professor, grade]):
        return jsonify({"Error": -1, "msg": "submissionId, id_professor y grade son necesarios"}), 400

    new_manual_review = ManualReview(
        submissionId=submissionId,
        id_professor=id_professor,
        grade=grade,
        comments=comments
    )

    try:
        with db.session.begin():
            db.session.add(new_manual_review)

        return jsonify({"code": 1, "msg": "Revisión manual creada exitosamente", "manualReview": new_manual_review.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la revisión manual: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la revisión manual"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    reviewId = data.get("reviewId")

    if not reviewId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        manual_review = ManualReview.query.get(reviewId)
        if not manual_review:
            return jsonify({"Error": -1, "msg": "Revisión manual no encontrada"}), 404

        db.session.delete(manual_review)
        db.session.commit()

        return jsonify({"code": 1, "msg": "Revisión manual eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar la revisión manual: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la revisión manual"}), 500

@jwt_required()
def update():
    update_data = request.get_json()
    reviewId = update_data.get("reviewId")
    grade = update_data.get("grade")
    comments = update_data.get("comments")

    if not reviewId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        manual_review = ManualReview.query.get(reviewId)
        if not manual_review:
            return jsonify({"Error": -1, "msg": "Revisión manual no encontrada"}), 404

        if grade is not None:
            manual_review.grade = grade
        if comments is not None:
            manual_review.comments = comments

        db.session.commit()

        return jsonify({
 "code": 1, 
            "msg": "Revisión manual actualizada exitosamente", 
            "manualReview": manual_review.to_dict()
        }), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar la revisión manual: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la revisión manual"}), 500

@jwt_required()
def showAll():
    try:
        manual_reviews = ManualReview.query.all()
        return jsonify([manual_review.to_dict() for manual_review in manual_reviews]), 200
    except Exception as e:
        print(f"Error al obtener todas las revisiones manuales: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener revisiones manuales"}), 500

@jwt_required()
def showID():
    reviewId = request.args.get("reviewId")

    if not reviewId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        manual_review = ManualReview.query.get(reviewId)
        if not manual_review:
            return jsonify({"Error": -1, "msg": "Revisión manual no encontrada"}), 404

        return jsonify({
            "code": 1,
            "msg": "Revisión manual encontrada",
            "manualReview": manual_review.to_dict()
        }), 200
    except Exception as e:
        print(f"Error al obtener la revisión manual: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener la revisión manual"}), 500

@jwt_required()
def showAllWithDetails():
    manual_reviews = db.session.query(ManualReview).join(Submission, ManualReview.submissionId == Submission.submissionId).join(User, ManualReview.id_professor == User.id_user).all()

    result = [
        {
            "reviewId": manual_review.reviewId,
            "submissionId": manual_review.submissionId,
            "id_professor": manual_review.id_professor,
            "grade": str(manual_review.grade),
            "comments": manual_review.comments,
            "reviewDate": manual_review.reviewDate,
            "submission": manual_review.submission.to_dict() if manual_review.submission else None,
            "professor": manual_review.professor.to_dict() if manual_review.professor else None
        }
        for manual_review in manual_reviews
    ]
    return jsonify(result), 200