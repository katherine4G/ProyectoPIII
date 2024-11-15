from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Auto_Correction import AutoCorrection
from app.models.Submission import Submission 
from app import db

@jwt_required()
def create():
    data = request.get_json()
    submissionId = data.get('submissionId')
    grade = data.get('grade')
    comments = data.get('comments')

    if not all([submissionId, grade]):
        return jsonify({"Error": -1, "msg": "submissionId y grade son necesarios"}), 400

    new_auto_correction = AutoCorrection(
        submissionId=submissionId,
        grade=grade,
        comments=comments
    )

    try:
        with db.session.begin():
            db.session.add(new_auto_correction)

        return jsonify({"code": 1, "msg": "Corrección automática creada exitosamente", "autoCorrection": new_auto_correction.to_dict()}), 201
    except Exception as e:
        print(f"Error al crear la corrección automática: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear la corrección automática"}), 500

@jwt_required()
def delete():
    data = request.get_json()
    autoCorrectionId = data.get("autoCorrectionId")

    if not autoCorrectionId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        auto_correction = AutoCorrection.query.get(autoCorrectionId)
        if not auto_correction:
            return jsonify({"Error": -1, "msg": "Corrección automática no encontrada"}), 404

        db.session.delete(auto_correction)
        db.session.commit()

        return jsonify({"code": 1, "msg": "Corrección automática eliminada exitosamente"}), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar la corrección automática: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar la corrección automática"}), 500

@jwt_required()
def update():
    update_data = request.get_json()
    autoCorrectionId = update_data.get("autoCorrectionId")
    grade = update_data.get("grade")
    comments = update_data.get("comments")

    if not autoCorrectionId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        auto_correction = AutoCorrection.query.get(autoCorrectionId)
        if not auto_correction:
            return jsonify({"Error": -1, "msg": "Corrección automática no encontrada"}), 404

        if grade is not None:
            auto_correction.grade = grade
        if comments is not None:
            auto_correction.comments = comments

        db.session.commit()

        return jsonify({
            "code": 1, 
            "msg": "Corrección automática actualizada exitosamente", 
            "autoCorrection": auto_correction.to_dict()
        }), 200
    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar la corrección automática: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar la corrección automática"}), 500

@jwt_required()
def showAll():
    try:
        auto_corrections = AutoCorrection.query.all()
        return jsonify([auto_correction.to_dict() for auto_correction in auto_corrections]), 200
    except Exception as e:
        print(f"Error al obtener todas las correcciones automáticas: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener correcciones automáticas"}), 500

@jwt_required()
def showID():
    autoCorrectionId = request.args.get("autoCorrectionId")

    if not autoCorrectionId:
        return jsonify({"Error": -1, "msg": "ID necesario"}), 400

    try:
        auto_correction = AutoCorrection.query.get(autoCorrectionId)
        if not auto_correction:
            return jsonify({"Error": -1, "msg": "Corrección automática no encontrada"}), 404

        return jsonify({
            "code": 1,
            "msg": "Corrección automática encontrada",
            "autoCorrection": auto_correction.to_dict()
        }), 200
    except Exception as e:
        print(f"Error al obtener la corrección automática: {e}")
        return jsonify({"Error": -1, "msg": "Error al obtener la corrección automática"}), 500

@jwt_required()
def showAllWithDetails():
    auto_corrections = db.session.query(AutoCorrection).join(Submission, AutoCorrection.submissionId == Submission.submissionId).all()

    result = [
        {
            "autoCorrectionId": auto_correction.autoCorrectionId,
            "submissionId": auto_correction.submissionId,
            "grade": str(auto_correction.grade),
            "comments": auto_correction.comments,
            "submission": auto_correction.submission.to_dict() if auto_correction.submission else None
        }
        for auto_correction in auto_corrections
    ]
    return jsonify(result), 200