package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.database.DatabaseService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.services.validation.*

class LoginPresenter(private val loginActivity: LoginContract.View) : LoginContract.Presenter {
    private val fsDatabaseService: DatabaseService = FsDatabaseService()

    override suspend fun login(username: String, password: String): UserModel? {
        var user: UserModel? = null
        if (isDataValid(username, password)) {
            user = fsDatabaseService.getClient(username)
            println("FOUND USER: $user")
        }
        println(user)
        return user
    }

    override fun isDataValid(username: String, password: String): Boolean {
        val validationMap = linkedMapOf(
            "username" to ValidationService.getStringState(username, PATTERN_USERNAME),
            "password" to ValidationService.getStringState(password, PATTERN_PASSWORD)
        )

        for ((key, value) in validationMap) {
            if (value == EMPTY) {
                showError(key, EMPTY_ERROR_MESSAGE)
                return false
            }
            if (value == INVALID) {
                showError(key, INVALID_ERROR_MESSAGE)
                return false
            }
        }

        return true
    }

    override fun showError(field: String, error: String) {
        when (field) {
            "username" -> loginActivity.showUsernameError(error)
            "password" -> loginActivity.showPasswordError(error)
        }
    }
}