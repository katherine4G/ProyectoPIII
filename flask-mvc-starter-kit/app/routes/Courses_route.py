from flask import Blueprint
from app.controllers import Courses_controller
prefix = 'Courseses'
route = Blueprint(prefix, __name__)

route.get('/Courseses')(Courses_controller.index)
route.get('/Courseses/create')(Courses_controller.create)
route.post('/Courseses')(Courses_controller.store)
route.get('/Courseses/<int:Courses_id>')(Courses_controller.show)
route.get('/Courseses/<int:Courses_id>/edit')(Courses_controller.edit)
route.post('/Courseses/<int:Courses_id>')(Courses_controller.update)
route.delete('/Courseses/<int:Courses_id>')(Courses_controller.delete)