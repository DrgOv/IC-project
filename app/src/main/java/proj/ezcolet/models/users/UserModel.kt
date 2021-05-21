package proj.ezcolet.models.users

import org.mindrot.jbcrypt.BCrypt
import proj.ezcolet.models.Model

abstract class UserModel(
    override var id: String = "",
    open var lastName: String = "",
    open var firstName: String = "",
    open var county: String = "",
    open var city: String = "",
    open var phone: String = "",
    open var username: String = "",
    open var password: String = "",
    var role: String = ""
) : Model(id) {
    fun doPasswordsMatch(password: String): Boolean {
        if (BCrypt.checkpw(password, this.password)) {
            return true
        }
        return false
    }
}