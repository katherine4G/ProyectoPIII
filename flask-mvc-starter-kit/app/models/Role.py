# app/models/Role.py
from app import db

class Role(db.Model):
    __tablename__ = 'Role'  

    role_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    role_name = db.Column(db.String(50), unique=True, nullable=False)

    def __repr__(self):
        return f'<Role {self.role_name}>'

    # # Método para crear roles por defecto
    # @classmethod
    # def create_default_roles(cls):
    #     # Agregar roles por defecto si no existen
    #     roles = ['Administrador', 'Profesor', 'Estudiante']
    #     for role_name in roles:
    #         if not cls.query.filter_by(role_name=role_name).first():
    #             role = cls(role_name=role_name)
    #             db.session.add(role)
    #     db.session.commit()
