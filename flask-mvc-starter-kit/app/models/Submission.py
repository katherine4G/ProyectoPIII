from flask_sqlalchemy import SQLAlchemy
from app import db

class Submission(db.Model):
    __tablename__ = 'Submission'
    
    submissionId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    assignmentId = db.Column(db.Integer, db.ForeignKey('Assignment.assignmentId', ondelete="CASCADE"), nullable=False)
    id_student = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False)
    submission_date = db.Column(db.DateTime, default=db.func.current_timestamp())
    file_path = db.Column(db.String(255))
    grade = db.Column(db.Numeric(5, 2))
    comments = db.Column(db.Text)
    taskReviewed = db.Column(db.Boolean, default=False)
    taskSubmitted = db.Column(db.Boolean, default=False)

    # Relaciones
    assignment = db.relationship("Assignment", backref=db.backref("submissions", lazy=True))
    student = db.relationship("User", backref=db.backref("submissions", lazy=True))

    def to_dict(self):
        return {
            "submissionId": self.submissionId,
            "assignmentId": self.assignmentId,
            "id_student": self.id_student,
            "submission_date": self.submission_date,
            "file_path": self.file_path,
            "grade": str(self.grade),
            "comments": self.comments,
            "taskReviewed": self.taskReviewed,
            "taskSubmitted": self.taskSubmitted,
            "assignment": self.assignment.to_dict() if self.assignment else None,
            "student": self.student.to_dict() if self.student else None
        }
    
    