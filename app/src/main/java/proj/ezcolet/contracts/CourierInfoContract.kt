package proj.ezcolet.contracts

import proj.ezcolet.models.users.CourierModel

interface CourierInfoContract {
    interface Presenter {
        suspend fun getUser(username: String): CourierModel?
        fun getFirstName(courier: CourierModel): String?
        fun getLastName(courier: CourierModel): String?
        fun getMonthlyOrders(courier: CourierModel): Int?
        fun getTotalOrders(courier: CourierModel): Int?
        fun getLikes(courier: CourierModel): Int?
        fun getDislikes(courier: CourierModel): Int?
        fun getDate(): String
        fun calculateRatio(likes: Double, dislikes: Double):Double
    }
}