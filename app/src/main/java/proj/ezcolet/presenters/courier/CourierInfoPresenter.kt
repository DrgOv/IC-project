package proj.ezcolet.presenters.courier

import proj.ezcolet.contracts.CourierInfoContract
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.database.FsCourierService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.views.courier.CourierInfoActivity

class CourierInfoPresenter(courierInfoActivity: CourierInfoActivity) :
    CourierInfoContract.Presenter {
   override suspend fun getUser(username:String): CourierModel? {
        val user = FsCourierService.getCourier(username)
        return user

    }

    override suspend fun getFirstName(courier:CourierModel): String? {
        var firstName=""
       firstName=courier.firstName.toString()
        return firstName

    }

    override suspend fun getLastName(courier:CourierModel): String? {
        var lastName=""
        lastName=courier.lastName.toString()
        return lastName

    }

    override suspend fun getRating(courier:CourierModel): Float? {
        var rating:Float
        rating= courier.rating.toFloat()
        return rating

    }

    override suspend fun getMonthlyOrders(courier:CourierModel): Int? {
        var orders:Int
        orders=courier.monthlyOrders
        return orders

    }

    override suspend fun getTotalOrders(courier:CourierModel): Int? {
        var orders:Int
        orders=courier.totalOrders
        return orders

    }

    override suspend fun getMaxRatings(courier:CourierModel): Int? {
        var maxRatings:Int
        maxRatings=courier.maxRatings
        return maxRatings

    }

    override suspend fun getNumberRatings(courier:CourierModel): Int? {
        var numberRatings:Int
        numberRatings=courier.numberRatings
        return numberRatings

    }
}