from .University_route import route as university_route  # Asegúrate de que el nombre sea correcto
from .user_route import auth_bp as auth_route

# Exporta los blueprints
main_routes = [university_route, auth_route]