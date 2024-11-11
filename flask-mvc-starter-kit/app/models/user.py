# app/models/user.py
from app import db
from werkzeug.security import generate_password_hash, check_password_hash

class User(db.Model):
    __tablename__ = 'user'

    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True, nullable=False)
    password = db.Column(db.String(200), nullable=False)

    def __repr__(self):
        return f'<User   {self.username}>'

    @classmethod
    def create(cls, username, password):
        new_user = cls(username=username, password=password)
        db.session.add(new_user)
        db.session.commit()
        return new_user