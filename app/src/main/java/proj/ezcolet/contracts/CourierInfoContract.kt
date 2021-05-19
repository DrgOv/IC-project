package proj.ezcolet.contracts

import proj.ezcolet.models.users.CourierModel

interface CourierInfoContract {
    interface Presenter {
        suspend fun getUser(username: String): CourierModel?
        suspend fun getFirstName(courier: CourierModel): String?
        suspend fun getLastName(courier: CourierModel): String?
        suspend fun getMonthlyOrders(courier: CourierModel): Int?
        suspend fun getTotalOrders(courier: CourierModel): Int?
        suspend fun getLikes(courier: CourierModel): Int?
        suspend fun getDislikes(courier: CourierModel): Int?
        fun getDate(): String
        fun calculateRatio(likes: Double, dislikes: Double):Double
    }
}