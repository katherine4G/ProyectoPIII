from . import db

class Courses(db.Model):
    __tablename__ = 'course'

    id = db.Column(db.Integer, primary_key=True)
    

    def __repr__(self):
        return f'<Courses {self.id}>'