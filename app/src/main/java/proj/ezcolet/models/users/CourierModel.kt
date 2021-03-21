package proj.ezcolet.models.users

import proj.ezcolet.models.Model

class CourierModel(
    override val id: String,
    override val lastName: String,
    override val firstName: String,
    override val county: String,
    override val city: String,
    override val phone: String,
    val company: String = "",
    override val username: String,
    override val password: String
) : UserModel(id, lastName, firstName, county, city, phone, username, password, "courier") {
    override fun toString(): String {
        return "CourierModel(id='$id', firstName='$firstName', lastName='$lastName', phone='$phone', company='$company', username='$username', password='$password')"
    }
}