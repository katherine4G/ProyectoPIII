#config.py

DEBUG = True
import os
BASE_DIR = os.path.abspath(os.path.dirname(__file__))  

connector = "mysql+mysqlconnector"
endpoint = "localhost" 
user = "root"
passwd = "1234"
database = "university_bd"

SQLALCHEMY_TRACK_MODIFICATIONS = False
DATABASE_CONNECT_OPTIONS = {}

SQLALCHEMY_DATABASE_URI = "{}://{}:{}@{}:3306/{}".format(connector, user, passwd, endpoint, database)
DATABASE_CONNECT_OPTIONS = {}

THREADS_PER_PAGE = 2

CSRF_ENABLED = True

# Secure keys
CSRF_SESSION_KEY = "secret"
SECRET_KEY = "secret"

# DEBUG = True

# import os

# class Config:
#     SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:1234@localhost/gestion_universitaria'
#     SQLALCHEMY_TRACK_MODIFICATIONS = False
#     SECRET_KEY = 'your_secret_key'  # Agrega una clave secreta para seguridad
#//////////////
# # Statement for enabling the development environment
# DEBUG = True

# # Define the application directory
# import os
# BASE_DIR = os.path.abspath(os.path.dirname(__file__))  

# # Define the database - we are working with
# # SQLite for this example
# SQLALCHEMY_TRACK_MODIFICATIONS = False
# SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(os.getcwd(), 'database\\db.sqlite3')
# DATABASE_CONNECT_OPTIONS = {}

# # Application threads. A common general assumption is
# # using 2 per available processor cores - to handle
# # incoming requests using one and performing background
# # operations using the other.
# THREADS_PER_PAGE = 2

# # Enable protection agains *Cross-site Request Forgery (CSRF)*
# CSRF_ENABLED     = True

# # Use a secure, unique and absolutely secret key for
# # signing the data. 
# CSRF_SESSION_KEY = "secret"

# # Secret key for signing cookies
# SECRET_KEY = "secret"