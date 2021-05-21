package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.services.validation.*

class LoginPresenter(private val loginActivity: LoginContract.View) : LoginContract.Presenter {

    override suspend fun login(username: String, password: String): UserModel? {
        if (isDataValid(username, password)) {
            val user = FsQueryingService.getUserByUsername(username)
            println("test")
            if (user != null) {
                println(user)
                if (user.doPasswordsMatch(password)) {
                    return user
                }
            }
        }

        loginActivity.showLoginError()
        return null
    }

    override fun isDataValid(username: String, password: String): Boolean {
        val validationMap = linkedMapOf(
            "username" to ValidationService.getStringState(username, PATTERN_USERNAME),
            "password" to ValidationService.getStringState(password, PATTERN_PASSWORD)
        )

        for ((key, value) in validationMap) {
            return when (value) {
                EMPTY -> {
                    showError(key, EMPTY_ERROR_MESSAGE)
                    false
                }
                INVALID -> {
                    showError(key, INVALID_ERROR_MESSAGE)
                    false
                }
                else -> true
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