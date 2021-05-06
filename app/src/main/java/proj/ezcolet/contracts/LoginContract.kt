package proj.ezcolet.contracts

import proj.ezcolet.models.users.UserModel

interface LoginContract {
    interface View {
        fun showUsernameError(error: String)
        fun showPasswordError(error: String)
        fun showLoginError()
        fun goToRegisterScreen()
        fun goToClientScreen()
        fun goToCourierScreen()
    }

    interface Presenter {
        suspend fun login(username: String, password: String): UserModel?
        fun isDataValid(username: String, password: String): Boolean
        fun showError(field: String, error: String)
    }
}