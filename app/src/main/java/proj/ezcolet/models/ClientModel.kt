package proj.ezcolet.models

class ClientModel(
    override val id: String,
    val lastName: String,
    val firstName: String,
    val street: String,
    val county: String,
    val city: String,
    val zipCode: String,
    val phone: String,
    val username: String,
    val password: String
) : Model(id) {
    override fun toString(): String {
        return "ClientModel(id='$id', lastName='$lastName', firstName='$firstName', street='$street', county='$county', city='$city', zipCode='$zipCode', phone='$phone', username='$username', password='$password')"
    }
}
