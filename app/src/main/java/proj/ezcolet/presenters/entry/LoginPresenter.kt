package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.views.entry.LoginActivity

class LoginPresenter(private val loginActivity: LoginContract.View) : LoginContract.Presenter {


    override fun isUsernameValid(username: String): Boolean {
        if (username.isEmpty()) {
            loginActivity.showUsernameError("Introdu username")
            return false
        }
        return true
    }

    override fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty()) {
            loginActivity.showPasswordError("Introdu parola")
            return false
        }
        return true
    }
}