from .University_route import route as university_route  
from .user_route import auth_bp as auth_route
from .Faculty_route import route_facu as faculty_route
from .Department_route import route_dept as depa_route
from .Course_route import route_course
from .Enrollment_student_route import route_enrollment_student
from .Enrollment_Professor_route import route_enrollment_professor
from .Student_Group_route import student_group_bp  
from .Assignment_route import assignment_route 
from .Submission_route import submission_route 
from .Registration_Request_route import registration_request_route 
from .Auto_Correction_route import auto_correction_route  
from .Manual_Review_route import manual_review_route  

# Blueprints exportados
main_routes = [
    university_route,
    auth_route,
    faculty_route,
    depa_route,
    route_course,
    route_enrollment_student,
    route_enrollment_professor,
    student_group_bp,
    assignment_route,
    submission_route,
    registration_request_route,
    auto_correction_route,
    manual_review_route 
]