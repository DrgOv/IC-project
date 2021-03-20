package proj.ezcolet.contracts

interface LoginContract {
    interface View {
        fun showUsernameError(error: String)
        fun showPasswordError(error: String)
        fun goToRegisterScreen()
        fun goToClientScreen()
        fun goToCourierScreen()
    }

    interface Presenter {
        fun login(username: String, password: String): String
        fun isDataValid(username: String, password: String): Boolean
        fun showError(field: String, error: String)
    }
}