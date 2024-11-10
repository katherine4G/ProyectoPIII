from . import db

class University(db.Model):
    __tablename__ = 'university'

    universityId = db.Column(db.Integer, primary_key=True, autoincrement=True, nullable=False)
    universityName = db.Column(db.String(255), nullable=False)
    uniCountry = db.Column(db.String(255), nullable=False)
    uniSede = db.Column(db.String(255), nullable=False)  
    uniAdress = db.Column(db.String(255), nullable=False)

    def __repr__(self):
        return f'<University {self.universityId}: {self.universityName}>'

    def to_dict(self):
        return {
            "universityId": self.universityId,
            "universityName": self.universityName,
            "uniCountry": self.uniCountry,
            "uniSede": self.uniSede,
            "uniAdress": self.uniAdress
        }

    @classmethod
    def create(cls, universityName, uniCountry, uniSede, uniAdress):
        try:
            new_university = cls(
                universityName=universityName,
                uniCountry=uniCountry,
                uniSede=uniSede,
                uniAdress=uniAdress
            )
            db.session.add(new_university)
            db.session.commit()
            return True, new_university
        except Exception as e:
            db.session.rollback()
            print(f"Error al crear la universidad: {e}")
            return False, f"Error al crear la universidad: {e}"

    @classmethod
    def delete(cls, universityId):
        university = cls.query.get(universityId)
        if university is None:
            return False, "La universidad no existe"
        try:
            db.session.delete(university)
            db.session.commit()
            return True, university
        except Exception as e:
            db.session.rollback()
            return False, f"Error al eliminar la universidad: {e}"

    @classmethod
    def update(cls, universityId, new_name=None, new_country=None, new_sede=None, new_address=None):
        university = cls.query.get(universityId)
        if university is None:
            return False, "La universidad no existe"
        
        try:
            if new_name:
                university.universityName = new_name
            if new_country:
                university.uniCountry = new_country
            if new_sede:
                university.uniSede = new_sede
            if new_address:
                university.uniAdress = new_address

            db.session.commit()
            return True, university
        except Exception as e:
            db.session.rollback()
            return False, f"Error al actualizar la universidad: {e}"

    @classmethod
    def select_by_id(cls, universityId):
        """Busca una universidad por ID."""
        university = cls.query.get(universityId)
        if university:
            return university
        return None, "La universidad no existe"
    
    #     @classmethod
    # def select_by_id(cls, university_id):
    #     """Busca una universidad por ID."""
    #     university = cls.query.get(university_id)
    #     return university if university else None

    # @classmethod
    # def delete_by_id(cls, university_id):
    #     """Elimina una universidad por ID."""
    #     try:
    #         university = cls.query.get(university_id)
    #         if not university:
    #             return False, "Universidad no encontrada"
            
    #         db.session.delete(university)
    #         db.session.commit()
    #         return True, "Universidad eliminada correctamente"
    #     except Exception as e:
    #         db.session.rollback()
    #         print(f"Error al eliminar la universidad: {e}")
    #         return False, "Error al eliminar la universidad"

    # def update(self, universityName=None, uniCountry=None, uniSede=None, uniAdress=None):
    #     """Actualiza los datos de la universidad con los valores proporcionados."""
    #     try:
    #         if universityName:
    #             self.universityName = universityName
    #         if uniCountry:
    #             self.uniCountry = uniCountry
    #         if uniSede:
    #             self.uniSede = uniSede
    #         if uniAdress:
    #             self.uniAdress = uniAdress
            
    #         db.session.commit()
    #         return True, "Universidad actualizada correctamente"
    #     except Exception as e:
    #         db.session.rollback()
    #         print(f"Error al actualizar la universidad: {e}")
    #         return False, "Error al actualizar la universidad"