package proj.ezcolet.contracts

import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel

interface CourierInfoContract {
    interface Presenter {
        suspend fun getUser(username: String): CourierModel?
        suspend fun getFirstName(courier: CourierModel): String?
        suspend fun getLastName(courier: CourierModel): String?
    }
}