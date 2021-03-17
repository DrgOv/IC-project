package proj.ezcolet.models

class CourierModel(
    override val id: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val company: String,
    val username: String,
    val password: String
) : Model(id) {
    override fun isDataValid() {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "CourierModel(id='$id', firstName='$firstName', lastName='$lastName', phone='$phone', company='$company', username='$username', password='$password')"
    }
}