package proj.ezcolet.presenters.admin

import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.services.database.FsDatabaseService

class AdminAddCourierPresenter {
    suspend fun addCourier(newCourier: CourierModel) {
        FsDatabaseService.addCourier(newCourier)
    }
}