package proj.ezcolet.models.users

import proj.ezcolet.services.database.FsCourierService
import proj.ezcolet.services.database.FsDatabaseService

class CourierModel(
    override var id: String = "",
    override var lastName: String = "",
    override var firstName: String = "",
    override var county: String = "",
    override var city: String = "",
    override var phone: String = "",
    override var username: String = "",
    override var password: String = "",
    var likes: Int = 0,
    var dislikes: Int = 0,
    var monthlyOrders: Int = 0,
    var totalOrders: Int = 0
) : UserModel(id, lastName, firstName, county, city, phone, username, password, "courier") {
    override fun toString(): String {
        return "CourierModel(id='$id', firstName='$firstName', lastName='$lastName', phone='$phone', username='$username', password='$password', monthlyOrders='$monthlyOrders', totalOrders='$totalOrders')"
    }

    suspend fun addLike() {
        likes++
        FsCourierService.updateCourier(this)
    }

    suspend fun removeLike() {
        likes--
        FsCourierService.updateCourier(this)
    }

    suspend fun addDislike() {
        dislikes++
        FsCourierService.updateCourier(this)
    }

    suspend fun removeDislike() {
        dislikes--
        FsCourierService.updateCourier(this)
    }
}