# model/Faculty.py
from app import db
from app.models.University import University

class Faculty(db.Model):
    __tablename__ = 'Faculty'
    
    facultyId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    facultyName = db.Column(db.String(255), nullable=False)
    facultyType = db.Column(db.String(255), nullable=False)
    universityId = db.Column(db.Integer, db.ForeignKey('University.universityId', ondelete="CASCADE"))

    university = db.relationship("University", backref=db.backref("faculties", lazy=True))

    def to_dict(self):
        return {
            "facultyId": self.facultyId,
            "facultyName": self.facultyName,
            "facultyType": self.facultyType,
            "university": self.university.to_dict() if self.university else None
        }
