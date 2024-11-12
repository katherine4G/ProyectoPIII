from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_jwt_extended import JWTManager
from flask_bcrypt import Bcrypt
from flask_migrate import Migrate

db = SQLAlchemy()
jwt = JWTManager()
flask_bcrypt = Bcrypt()
migrate = Migrate()  

def init_app():
    app = Flask(__name__)

    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:1234@localhost/university_bd'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    app.config['SECRET_KEY'] = 'secret'
    app.config['JWT_SECRET_KEY'] = 'jwtsecret'

    db.init_app(app)
    jwt.init_app(app)
    flask_bcrypt.init_app(app)  # Inicializa Flask-Bcrypt
    migrate.init_app(app, db)   # Configura Flask-Migrate con tu aplicación y base de datos

    with app.app_context():
        try:
            db.session.execute('SELECT 1')
            print("Conexión exitosa a la base de datos.")
        except Exception as e:
            print(f"Error al conectar a la base de datos: {e}")

    return app

# from flask import Flask
# from flask_sqlalchemy import SQLAlchemy
# from flask_jwt_extended import JWTManager
# from flask_bcrypt import Bcrypt

# db = SQLAlchemy()
# jwt = JWTManager()
# flask_bcrypt = Bcrypt()

# def init_app():
#     app = Flask(__name__)

#     app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:1234@localhost/university_bd'
#     app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
#     app.config['SECRET_KEY'] = 'secret'
#     app.config['JWT_SECRET_KEY'] = 'jwtsecret'

#     db.init_app(app)
#     jwt.init_app(app)
#     flask_bcrypt.init_app(app)  # Inicializa Flask-Bcrypt

#     with app.app_context():
#         try:
#             db.session.execute('SELECT 1')
#             print("Conexión exitosa a la base de datos.")
#         except Exception as e:
#             print(f"Error al conectar a la base de datos: {e}")



#     return app