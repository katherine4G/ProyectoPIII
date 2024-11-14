from flask_sqlalchemy import SQLAlchemy
from app import db
class University(db.Model):
    __tablename__ = 'University'
    
    universityId = db.Column(db.Integer, primary_key=True)
    universityName = db.Column(db.String(255), nullable=False)
    uniCountry = db.Column(db.String(255), nullable=False)
    uniSede = db.Column(db.String(255), nullable=False)
    uniAdress = db.Column(db.String(255), nullable=False)

    def to_dict(self):
        return {
            "universityId": self.universityId,
            "universityName": self.universityName,
            "uniCountry": self.uniCountry,
            "uniSede": self.uniSede,
            'uniAdress': self.uniAdress
        }
