from flask_sqlalchemy import SQLAlchemy
from app import db

class ManualReview(db.Model):
    __tablename__ = 'ManualReview'
    
    reviewId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    submissionId = db.Column(db.Integer, db.ForeignKey('Submission.submissionId', ondelete="CASCADE"), nullable=False)
    id_professor = db.Column(db.String(50), db.ForeignKey('User.id_user', ondelete="CASCADE"), nullable=False)
    grade = db.Column(db.Numeric(5, 2), nullable=False)
    comments = db.Column(db.Text, nullable=True)
    reviewDate = db.Column(db.DateTime, default=db.func.current_timestamp())

    # Relaciones
    submission = db.relationship("Submission", backref=db.backref("manual_reviews", lazy=True))
    professor = db.relationship("User", backref=db.backref("manual_reviews", lazy=True))

    def to_dict(self):
        return {
            "reviewId": self.reviewId,
            "submissionId": self.submissionId,
            "id_professor": self.id_professor,
            "grade": str(self.grade),  # Convertir a string para JSON
            "comments": self.comments,
            "reviewDate": self.reviewDate,
            "submission": self.submission.to_dict() if self.submission else None,
            "professor": self.professor.to_dict() if self.professor else None
        }