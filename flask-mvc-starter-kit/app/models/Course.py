from app import db
from app.models.Department import Department

class Course(db.Model):
    __tablename__ = 'Course'
    
    NRC = db.Column(db.String(20), primary_key=True)
    codeCourse = db.Column(db.String(50), nullable=False)
    nameCourse = db.Column(db.String(255), nullable=False)
    idDepartment = db.Column(db.Integer, db.ForeignKey('Department.idDepartment', ondelete="SET NULL"))
    
    # Relación con Department
    department = db.relationship("Department", backref=db.backref("courses", lazy=True))

    def to_dict(self):
        return {
            "NRC": self.NRC,
            "codeCourse": self.codeCourse,
            "nameCourse": self.nameCourse,
            "department": self.department.to_dict() if self.department else None
        }
