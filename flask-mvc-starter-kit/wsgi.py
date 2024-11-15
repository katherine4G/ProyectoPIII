from app import init_app
from app.routes.user_route import auth_bp
from app.routes.University_route import route as university_route   
from app.routes.Faculty_route import route_facu as faculty_route
from app.routes.Department_route import route_dept as depa_route
from app.routes.Course_route import route_course
from app.routes.Enrollment_student_route import route_enrollment_student
from app.routes.Enrollment_Professor_route import route_enrollment_professor
from app.routes.Student_Group_route import student_group_bp  
from app.routes.Assignment_route import assignment_route 
from app.routes.Submission_route import submission_route 
from app.routes.Registration_Request_route import registration_request_route 
from app.routes.Auto_Correction_route import auto_correction_route
from app.routes.Manual_Review_route import manual_review_route  

app = init_app()

# Registra el Blueprint
app.register_blueprint(auth_bp)
app.register_blueprint(university_route)
app.register_blueprint(faculty_route)
app.register_blueprint(depa_route)
app.register_blueprint(route_course)
app.register_blueprint(route_enrollment_student)
app.register_blueprint(route_enrollment_professor)
app.register_blueprint(student_group_bp)
app.register_blueprint(assignment_route)
app.register_blueprint(submission_route)
app.register_blueprint(registration_request_route)
app.register_blueprint(auto_correction_route)
app.register_blueprint(manual_review_route)

if __name__ == '__main__':
    app.run(debug=True)