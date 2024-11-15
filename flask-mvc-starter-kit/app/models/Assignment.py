from flask_sqlalchemy import SQLAlchemy
from app import db

class Assignment(db.Model):
    __tablename__ = 'Assignment'  
    
    assignmentId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    NRC = db.Column(db.String(20), db.ForeignKey('Course.NRC', ondelete="CASCADE"), nullable=False) 
    id_professor = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False) 
    title = db.Column(db.String(255), nullable=False)
    description = db.Column(db.Text)
    due_date = db.Column(db.Date)
    file_path = db.Column(db.String(255))
    groupId = db.Column(db.Integer, db.ForeignKey('StudentGroup.groupId', ondelete="CASCADE")) 

    # Relaciones
    professor = db.relationship("User", backref=db.backref("assignments", lazy=True))
    course = db.relationship("Course", backref=db.backref("assignments", lazy=True))
    group = db.relationship("Student_Group", backref=db.backref("assignments", lazy=True))

    def to_dict(self):
        return {
            "assignmentId": self.assignmentId,
            "NRC": self.NRC,
            "id_professor": self.id_professor,
            "title": self.title,
            "description": self.description,
            "due_date": self.due_date,
            "file_path": self.file_path,
            "groupId": self.groupId,
            "professor": self.professor.to_dict() if self.professor else None,
            "course": self.course.to_dict() if self.course else None,
            "group": self.group.to_dict() if self.group else None
        }