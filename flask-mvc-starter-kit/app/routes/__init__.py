#routes/__init__.py
from .University_route import route as university_route  
from .user_route import auth_bp as auth_route
from .Faculty_route import route_facu as faculty_route
from .Department_route import route_dept as depa_route

# blueprints exportados:
main_routes = [university_route, auth_route,faculty_route,depa_route]