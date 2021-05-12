package proj.ezcolet.models.users

class CourierModel(
    override var id: String = "",
    override var lastName: String = "",
    override var firstName: String = "",
    override var county: String = "",
    override var city: String = "",
    override var phone: String = "",
    override var username: String = "",
    override var password: String = "",
    var rating: Float = 0f,
    var maxRatings: Int = 0,
    var numberRatings: Int = 0,
    var monthlyOrders: Int = 0,
    var totalOrders: Int = 0
) : UserModel(id, lastName, firstName, county, city, phone, username, password, "courier") {
    override fun toString(): String {
        return "CourierModel(id='$id', firstName='$firstName', lastName='$lastName', phone='$phone', username='$username', password='$password', rating='$rating', maxRatings'$maxRatings',numberRatings='$numberRatings', monthlyOrders='$monthlyOrders', totalOrders='$totalOrders')"
    }
}