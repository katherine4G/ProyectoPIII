# run.py
from app import init_app
from app.routes.user_route import auth_bp  # Blueprint de autenticación
from app.routes.University_route import route as university_route   # Asegúrate que el nombre sea correcto

app = init_app()

# Registra el Blueprint
app.register_blueprint(auth_bp)
app.register_blueprint(university_route)

if __name__ == '__main__':
    app.run(debug=True)
