from flask import request, jsonify
from flask_jwt_extended import jwt_required
from app.models.Course import Course
from app.models.Department import Department
from app import db

@jwt_required()
def create_course():
    data = request.get_json()
    try:
        NRC = data.get('NRC')
        codeCourse = data.get('codeCourse')
        nameCourse = data.get('nameCourse')
        idDepartment = data.get('idDepartment')

        department = Department.query.get(idDepartment)
        if idDepartment and not department:
            return jsonify({"Error": -1, "msg": "Departamento no encontrado"}), 404

        new_course = Course(
            NRC=NRC,
            codeCourse=codeCourse,
            nameCourse=nameCourse,
            idDepartment=idDepartment
        )

        db.session.add(new_course)
        db.session.commit()
        return jsonify({"msg": "Curso creado exitosamente", "course": new_course.to_dict()}), 201

    except Exception as e:
        db.session.rollback()
        print(f"Error al crear el curso: {e}")
        return jsonify({"Error": -1, "msg": "Error al crear el curso"}), 500

@jwt_required()
def delete_course():
    data = request.get_json()
    NRC = data.get("NRC")
    
    if not NRC:
        return jsonify({"Error": -1, "msg": "NRC necesario"}), 400

    try:
        course = Course.query.get(NRC)
        if not course:
            return jsonify({"Error": -1, "msg": "Curso no encontrado"}), 404

        db.session.delete(course)
        db.session.commit()
        return jsonify({"msg": "Curso eliminado exitosamente"}), 200

    except Exception as e:
        db.session.rollback()
        print(f"Error al eliminar el curso: {e}")
        return jsonify({"Error": -1, "msg": "Error al eliminar el curso"}), 500

@jwt_required()
def update_course():
    data = request.get_json()
    NRC = data.get("NRC")
    codeCourse = data.get("codeCourse")
    nameCourse = data.get("nameCourse")
    idDepartment = data.get("idDepartment")

    if not NRC:
        return jsonify({"Error": -1, "msg": "NRC necesario"}), 400

    try:
        course = Course.query.get(NRC)
        if not course:
            return jsonify({"Error": -1, "msg": "Curso no encontrado"}), 404
        
        # Verificar el departamento si se va a actualizar
        if idDepartment:
            department = Department.query.get(idDepartment)
            if not department:
                return jsonify({"Error": -1, "msg": "Departamento no encontrado"}), 404
            course.idDepartment = idDepartment

        course.codeCourse = codeCourse or course.codeCourse
        course.nameCourse = nameCourse or course.nameCourse
        db.session.commit()
        return jsonify({"msg": "Curso actualizado exitosamente", "course": course.to_dict()}), 200

    except Exception as e:
        db.session.rollback()
        print(f"Error al actualizar el curso: {e}")
        return jsonify({"Error": -1, "msg": "Error al actualizar el curso"}), 500

@jwt_required()
def showID():
    nrc = request.args.get("NRC")

    if not nrc:
        return jsonify({"Error": -1, "msg": "NRC necesario"}), 400

    # Obtener el curso por NRC
    course = Course.query.filter_by(NRC=nrc).first()
    if not course:
        return jsonify({"Error": -1, "msg": "Curso no encontrado"}), 404

    # Convertir el curso a un diccionario
    course_dict = course.to_dict()

    # Obtener detalles del departamento, facultad y universidad
    if course_dict['department']:
        department = course_dict['department']
        if department['faculty']:
            faculty = department['faculty']
            if faculty['university']:
                university = faculty['university']
                course_dict['department']['faculty']['university'] = university

    return jsonify({
        "code": 1,
        "msg": "Curso encontrado",
        "course": course_dict
    }), 200
@jwt_required()
def show_all_courses():
    courses = Course.query.all()
    return jsonify([course.to_dict() for course in courses]), 200

@jwt_required()
def showAllWithDepartment():
    courses_with_department = db.session.query(Course).join(Department).all()

    result = [
        {
            "NRC": course.NRC,
            "codeCourse": course.codeCourse,
            "nameCourse": course.nameCourse,
            "department": course.department.to_dict() if course.department else None
        }
        for course in courses_with_department
    ]

    return jsonify(result), 200

@jwt_required()
def getAllWithDepartmentAndFacultyAndUniversity():
    courses = db.session.query(Course).join(Department).all()
    result = []
    for course in courses:
        course_data = {
            "NRC": course.NRC,
            "codeCourse": course.codeCourse,
            "nameCourse": course.nameCourse,
            "department": None,
            "faculty": None,
            "university": None
        }
  
        if course.department:
            department = course.department
            course_data["department"] = department.to_dict()

            if department.faculty:
                faculty = department.faculty
                course_data["faculty"] = faculty.to_dict()

                if faculty.university:
                    university = faculty.university
                    course_data["university"] = university.to_dict()

        result.append(course_data)

    return jsonify(result), 200