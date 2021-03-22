package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.services.validation.*

class LoginPresenter(private val loginActivity: LoginContract.View) : LoginContract.Presenter {

    override suspend fun login(username: String, password: String): UserModel? {
        if (isDataValid(username, password)) {
            val user = if (username.startsWith("curier_")) {
                println(FsDatabaseService.getCourier(username))
                FsDatabaseService.getCourier(username)
            } else {
                println(FsDatabaseService.getClient(username))
                FsDatabaseService.getClient(username)
            }
            if (user != null) {
                if (user.doPasswordsMatch(password)) {
                    return user
                }
            }
        }
        return null
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