package proj.ezcolet.views.entry

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
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
import proj.ezcolet.views.client.ClientHomeActivity
import proj.ezcolet.views.courier.CourierHomeActivity
import kotlin.coroutines.CoroutineContext

class LoginActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(), LoginContract.View, CoroutineScope {
    private lateinit var loginPresenter: LoginContract.Presenter
    private lateinit var binding: EntryLoginActivityBinding

    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBtn: Button
    private lateinit var toRegisterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)
        loginPresenter = LoginPresenter(this)
        binding = EntryLoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usernameET = binding.usernameEditText
        passwordET = binding.passwordEditText
        loginBtn = binding.loginBtn
        toRegisterBtn = binding.toRegisterBtn

        loginBtn.setOnClickListener() {
            launch {
                login()
            }
        }

        toRegisterBtn.setOnClickListener() {
            goToRegisterScreen()
        }

        binding.toUserBtn.setOnClickListener() {
            goToClientScreen()
        }

        binding.toCourierBtn.setOnClickListener() {
            goToCourierScreen()
        }
    }

    private suspend fun login() {
        val username = usernameET.text.toString()
        val password = passwordET.text.toString()

        goToUserScreen(loginPresenter.login(username, password))
    }

    override fun showUsernameError(error: String) {
        usernameET.error = error
    }

    override fun showPasswordError(error: String) {
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
        ViewService.setView(this, ClientHomeActivity())
    }

    override fun goToCourierScreen() {
        ViewService.setView(this, CourierHomeActivity())
    }
}
