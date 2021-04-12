package proj.ezcolet.views.entry

import android.graphics.PixelFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.databinding.EntryLoginActivityBinding
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.presenters.entry.LoginPresenter
import proj.ezcolet.services.ViewService
import proj.ezcolet.views.admin.AdminHomeActivity
import proj.ezcolet.views.client.ClientHomeActivity
import proj.ezcolet.views.courier.CourierHomeActivity
import kotlin.coroutines.CoroutineContext

class LoginActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(),
    LoginContract.View, CoroutineScope {
    private lateinit var loginPresenter: LoginContract.Presenter
    private lateinit var binding: EntryLoginActivityBinding

    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBtn: Button
    private lateinit var toRegisterBtn: Button
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)
        binding = EntryLoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginPresenter = LoginPresenter(this)
        bindViews()
        setListeners()
    }

    private fun bindViews() {
        usernameET = binding.usernameEditText
        passwordET = binding.passwordEditText
        loginBtn = binding.loginBtn
        toRegisterBtn = binding.toRegisterBtn
    }

    private fun setListeners() {
        loginBtn.setOnClickListener() {
            launch {
                login()
            }
        }

        toRegisterBtn.setOnClickListener() {
            goToRegisterScreen()
        }
    }

    private suspend fun login() {
        username = usernameET.text.toString()
        val password = passwordET.text.toString()

        if (username == "admin" && password == "admin") {
            goToAdminScreen()
        } else {
            goToUserScreen(loginPresenter.login(username, password))
        }
    }

    override fun showUsernameError(error: String) {
        usernameET.error = error
    }

    override fun showPasswordError(error: String) {
        println("test");
        passwordET.error = error
    }

    override fun goToRegisterScreen() {
        ViewService.setView(this, RegisterActivity())
    }

    private fun goToUserScreen(user: UserModel?) {
        when (user) {
            is ClientModel -> goToClientScreen()
            is CourierModel -> goToCourierScreen()
        }
    }

    override fun goToClientScreen() {
        ViewService.setViewAndId(this, ClientHomeActivity(), username)
    }

    override fun goToCourierScreen() {
        ViewService.setViewAndId(this, CourierHomeActivity(), username)
    }

    override fun goToAdminScreen() {
        ViewService.setView(this, AdminHomeActivity())
    }
}
