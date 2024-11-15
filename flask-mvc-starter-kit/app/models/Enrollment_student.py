from flask_sqlalchemy import SQLAlchemy
from app.models.user import User
from app import db

class EnrollmentStudent(db.Model):
    __tablename__ = 'Enrollment_student'
    
    enroll_studentId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_student = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False)
    NRC = db.Column(db.String(20), db.ForeignKey('Course.NRC', ondelete="CASCADE"), nullable=False)

    # Relaciones
    student = db.relationship("User", backref=db.backref("enrollments", lazy=True))
    course = db.relationship("Course", backref=db.backref("enrollments", lazy=True))

    def to_dict(self):
        return {
            "enroll_studentId": self.enroll_studentId,
            "id_student": self.id_student,
            "NRC": self.NRC,
            "student": self.student.to_dict() if self.student else None,
            "course": self.course.to_dict() if self.course else None
        }