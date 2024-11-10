
from app import init_app

app = init_app()

if __name__ == '__main__':
    app.debug = True
    app.run()

# from app import create_app  # Cambiar 'init_app' por 'create_app'

# app = create_app()  # Usar 'create_app' en lugar de 'init_app'

# if __name__ == '__main__':
#     app.debug = True
#     app.run()
