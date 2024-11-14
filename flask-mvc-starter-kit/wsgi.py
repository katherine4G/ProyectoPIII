from app import init_app
from app.routes.user_route import auth_bp  # Blueprint de autenticación
from app.routes.University_route import route as university_route   
from app.routes.Faculty_route import route_facu as faculty_route
from app.routes.Department_route import route_dept as depa_route
from app.routes.Course_route import route_course
from app.routes.Enrollment_student_route import route_enrollment_student
from app.routes.Enrollment_Professor_route import route_enrollment_professor as enrollment_studen_route

app = init_app()

# Registra el Blueprint
app.register_blueprint(auth_bp)
app.register_blueprint(university_route)
app.register_blueprint(faculty_route)
app.register_blueprint(depa_route)
app.register_blueprint(route_course)
app.register_blueprint(route_enrollment_student)
app.register_blueprint(enrollment_studen_route)


if __name__ == '__main__':
    app.run(debug=True)


