from flask_sqlalchemy import SQLAlchemy
from app import db

class AutoCorrection(db.Model):
    __tablename__ = 'AutoCorrection'
    
    autoCorrectionId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    submissionId = db.Column(db.Integer, db.ForeignKey('Submission.submissionId', ondelete="CASCADE"), nullable=False)
    grade = db.Column(db.Numeric(5, 2), nullable=False)
    comments = db.Column(db.Text, nullable=True)

    # Relaciones
    submission = db.relationship("Submission", backref=db.backref("auto_corrections", lazy=True))

    def to_dict(self):
        return {
            "autoCorrectionId": self.autoCorrectionId,
            "submissionId": self.submissionId,
            "grade": str(self.grade),  # Convertir a string para JSON
            "comments": self.comments,
            "submission": self.submission.to_dict() if self.submission else None
        }