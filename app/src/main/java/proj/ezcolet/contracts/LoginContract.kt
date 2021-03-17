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
        fun isUsernameValid(username: String): Boolean
        fun isPasswordValid(password: String): Boolean
    }
}