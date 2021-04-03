package proj.ezcolet.presenters.courier

import proj.ezcolet.contracts.CourierInfoContract
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.views.courier.CourierInfoActivity

class CourierInfoPresenter(courierInfoActivity: CourierInfoActivity) :
    CourierInfoContract.Presenter {
   override suspend fun getUser(username:String): CourierModel? {
        val user = FsDatabaseService.getCourier(username)
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
}