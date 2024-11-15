# models/Registration_Request.py
from flask_sqlalchemy import SQLAlchemy
from app import db

class RegistrationRequest(db.Model):
    __tablename__ = 'RegistrationRequest'
    
    requestId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_user = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False)
    request_type = db.Column(db.Enum('Profesor', 'Estudiante'), nullable=False)
    status = db.Column(db.Enum('Pendiente', 'Aceptada', 'Denegada'), default='Pendiente')
    requested_at = db.Column(db.DateTime, default=db.func.current_timestamp())

    # Relaciones 
    user = db.relationship("User", backref=db.backref("registration_requests", lazy=True))

    def to_dict(self):
        return {
            "requestId": self.requestId,
            "id_user": self.id_user,
            "request_type": self.request_type,
            "status": self.status,
            "requested_at": self.requested_at,
            "user": self.user.to_dict() if self.user else None
        }