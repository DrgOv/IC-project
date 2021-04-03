package proj.ezcolet.models.users

class CourierModel(
    override val id: String = "",
    override val lastName: String = "",
    override val firstName: String = "",
    override val county: String = "",
    override val city: String = "",
    override val phone: String = "",
    override val username: String = "",
    override val password: String = ""
) : UserModel(id, lastName, firstName, county, city, phone, username, password, "courier") {
    override fun toString(): String {
        return "CourierModel(id='$id', firstName='$firstName', lastName='$lastName', phone='$phone', username='$username', password='$password')"
    }
}