from flask import Blueprint
from app.controllers import user_controller
prefix = 'users'
route = Blueprint(prefix, __name__)

route.get('user/register')(user_controller.register)
route.post('user/register')(user_controller.register)
route.get('user/login')(user_controller.login)
route.post('user/login')(user_controller.login)
route.get('user/dashboard')(user_controller.dashboard)
route.get('user/logout')(user_controller.logout)

# route.get('/')(user_controller.index)
# route.get('/create')(user_controller.create)
# route.post('/')(user_controller.store)
# route.get('/<int:user_id>')(user_controller.show)
# route.get('/<int:user_id>/edit')(user_controller.edit)
# route.post('/<int:user_id>')(user_controller.update)
# route.delete('/<int:user_id>')(user_controller.delete)