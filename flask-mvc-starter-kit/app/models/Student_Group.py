from flask_sqlalchemy import SQLAlchemy
from app import db

class Student_Group(db.Model):
    __tablename__ = 'StudentGroup'
    
    groupId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    NRC = db.Column(db.String(20), db.ForeignKey('Course.NRC', ondelete="CASCADE"), nullable=False)
    groupName = db.Column(db.String(255), nullable=False)
    id_professor = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False)

    # Relaciones
    professor = db.relationship("User", backref=db.backref("student_groups", lazy=True))
    course = db.relationship("Course", backref=db.backref("student_groups", lazy=True))

    def to_dict(self):
        return {
            "groupId": self.groupId,
            "NRC": self.NRC,
            "groupName": self.groupName,
            "id_professor": self.id_professor,
            "professor": self.professor.to_dict() if self.professor else None,
            "course": self.course.to_dict() if self.course else None
        }