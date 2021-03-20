package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.models.ClientModel
import proj.ezcolet.services.database.DatabaseService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.services.validation.*

class LoginPresenter(private val loginActivity: LoginContract.View) : LoginContract.Presenter {
    private val fsDatabaseService: DatabaseService = FsDatabaseService()

    override fun login(username: String, password: String): String {
        if (isDataValid(username, password)) {
            println("DATE VALIDE")
            return "client"
        }
        println("DATE INVALIDE")
        return ""
    }

    override fun isDataValid(username: String, password: String): Boolean {
        val validationMap = hashMapOf(
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