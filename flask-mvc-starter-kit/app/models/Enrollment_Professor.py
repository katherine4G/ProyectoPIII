from flask_sqlalchemy import SQLAlchemy
from app import db

class Enrollment_Professor(db.Model):
    __tablename__ = 'Enrollment_professor'
    
    enroll_profeId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_professor = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False)
    NRC = db.Column(db.String(20), db.ForeignKey('Course.NRC', ondelete="CASCADE"), nullable=False)

    # Relaciones
    professor = db.relationship("User", backref=db.backref("enrollments_professor", lazy=True))
    course = db.relationship("Course", backref=db.backref("enrollments_professor", lazy=True))

    def to_dict(self):
        return {
            "enroll_profeId": self.enroll_profeId,
            "id_professor": self.id_professor,
            "NRC": self.NRC,
            "professor": self.professor.to_dict() if self.professor else None,
            "course": self.course.to_dict() if self.course else None
        }