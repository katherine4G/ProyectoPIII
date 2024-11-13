from app import db
from app.models.Faculty import Faculty

class Department(db.Model):
    __tablename__ = 'Department'
    
    idDepartment = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nameDepartment = db.Column(db.String(255), nullable=False)
    facultyId = db.Column(db.Integer, db.ForeignKey('Faculty.facultyId', ondelete="CASCADE"), nullable=False)

    faculty = db.relationship("Faculty", backref=db.backref("departments", lazy=True))

    def to_dict(self):
        return {
            "idDepartment": self.idDepartment,
            "nameDepartment": self.nameDepartment,
            "faculty": self.faculty.to_dict() if self.faculty else None
        }
