package proj.ezcolet.presenters.admin

import org.mindrot.jbcrypt.BCrypt
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.services.database.FsCourierService

class AdminAddCourierPresenter {
    suspend fun addCourier(newCourier: CourierModel) {
        newCourier.password = BCrypt.hashpw(newCourier.password, BCrypt.gensalt())
        FsCourierService.addCourier(newCourier)
    }
}
