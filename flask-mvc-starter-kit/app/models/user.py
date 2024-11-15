from app import db
from app.models.Role import Role

from werkzeug.security import generate_password_hash

class User(db.Model):
    __tablename__ = 'User'

    id_user = db.Column(db.String(50), primary_key=True)
    nameUser = db.Column(db.String(255), unique=True, nullable=False)
    lastName = db.Column(db.String(255), nullable=False)
    email = db.Column(db.String(255), unique=True, nullable=False)
    password = db.Column(db.String(255), nullable=False)
    role_id = db.Column(db.Integer, db.ForeignKey('Role.role_id'), nullable=False)

    Role = db.relationship('Role', backref=db.backref('User', lazy=True))

    def __repr__(self):
        return f'<User {self.nameUser}>'

    @classmethod
    def create(cls, id_user, nameUser, lastName, email, password, role_id):
        hashed_password = generate_password_hash(password)
        new_user = cls(
            id_user=id_user,
            nameUser=nameUser,
            lastName=lastName,
            email=email,
            password=hashed_password,
            role_id=role_id 
        )
        db.session.add(new_user)
        db.session.commit()
        return new_user
    
    def to_dict(self):
        return {
            "id_user": self.id_user,
            "nameUser ": self.nameUser ,
            "lastName": self.lastName,
            "email": self.email,
            "role_id": self.role_id
        }