package proj.ezcolet.presenters.admin

import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.services.database.FsCourierService

class AdminAddCourierPresenter {
    suspend fun addCourier(newCourier: CourierModel) {
        FsCourierService.addCourier(newCourier)
    }
}