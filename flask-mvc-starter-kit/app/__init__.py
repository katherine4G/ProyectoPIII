# app/__init__.py
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_jwt_extended import JWTManager

db = SQLAlchemy()
jwt = JWTManager()

def init_app(): #se va al wsgi.py
    app = Flask(__name__)

    # Configuración de la base de datos y JWT
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:1234@localhost/university_bd'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    app.config['SECRET_KEY'] = 'secret'
    app.config['JWT_SECRET_KEY'] = 'jwtsecret'

    # Inicializar extensiones
    db.init_app(app)
    jwt.init_app(app)

    with app.app_context():
        try:
            db.session.execute('SELECT 1')
            print("Conexión exitosa a la base de datos.")
        except Exception as e:
            print(f"Error al conectar a la base de datos: {e}")

        from .routes import main_routes, University_route
       # app.register_blueprint(main_routes)
        app.register_blueprint(University_route.route, name='university_unique')


    return app
