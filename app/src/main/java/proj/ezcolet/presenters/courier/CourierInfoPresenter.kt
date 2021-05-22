package proj.ezcolet.presenters.courier

import proj.ezcolet.contracts.CourierInfoContract
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.services.database.FsCourierService
import proj.ezcolet.views.courier.CourierInfoActivity
import java.util.*

class CourierInfoPresenter(courierInfoActivity: CourierInfoActivity) :
    CourierInfoContract.Presenter {
    override suspend fun getUser(username: String): CourierModel? {
        val user = FsCourierService.getCourier(username)
        return user

    }

    override fun getFirstName(courier: CourierModel): String {
        var firstName = ""
        firstName = courier.firstName.toString()
        return firstName

    }

    override fun getLastName(courier: CourierModel): String {
        var lastName = ""
        lastName = courier.lastName.toString()
        return lastName

    }

    override fun getMonthlyOrders(courier: CourierModel): Int {
        var orders: Int
        orders = courier.monthlyOrders
        return orders

    }

    override fun getTotalOrders(courier: CourierModel): Int {
        var orders: Int
        orders = courier.totalOrders
        return orders

    }

    override fun getLikes(courier: CourierModel): Int {
        var likes: Int
        likes = courier.likes
        return likes

    }

    override fun getDislikes(courier: CourierModel): Int {
        var dislikes: Int
        dislikes = courier.dislikes
        return dislikes

    }

    override fun getDate(): String {
        val calendar: Calendar = Calendar.getInstance()
        val month: String =
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("ro"))
        return month
    }

    override fun calculateRatio(likes: Double, dislikes: Double): Double{
        val ratio = likes/dislikes
        return ratio
    }
}