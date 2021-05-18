package proj.ezcolet.contracts

import proj.ezcolet.models.users.CourierModel

interface CourierInfoContract {
    interface Presenter {
        suspend fun getUser(username: String): CourierModel?
        suspend fun getFirstName(courier: CourierModel): String?
        suspend fun getLastName(courier: CourierModel): String?
        suspend fun getMonthlyOrders(courier: CourierModel): Int?
        suspend fun getTotalOrders(courier: CourierModel): Int?
    }
}