package proj.ezcolet.models.users

import proj.ezcolet.models.Model

abstract class UserModel(
    override val id: String = "",
    open val lastName: String = "",
    open val firstName: String = "",
    open val county: String = "",
    open val city: String = "",
    open val phone: String = "",
    open val username: String = "",
    open val password: String = "",
    val role: String = ""
) : Model(id) {
}