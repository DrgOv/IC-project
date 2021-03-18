package proj.ezcolet.views.entry

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import proj.ezcolet.contracts.LoginContract
import proj.ezcolet.databinding.EntryLoginActivityBinding
import proj.ezcolet.presenters.entry.LoginPresenter
import proj.ezcolet.services.ViewService
import proj.ezcolet.views.client.ClientHomeActivity
import proj.ezcolet.views.courier.CourierHomeActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val loginPresenter: LoginContract.Presenter = LoginPresenter(this)
    private lateinit var binding: EntryLoginActivityBinding

    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBtn: Button
    private lateinit var toRegisterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)
        binding = EntryLoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usernameET = binding.usernameEditText
        passwordET = binding.passwordEditText
        loginBtn = binding.loginBtn
        toRegisterBtn = binding.toRegisterBtn

        loginBtn.setOnClickListener() {
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

    override fun showUsernameError(error: String) {
        usernameET.error = error
    }

    override fun showPasswordError(error: String) {
        passwordET.error = error
    }

    override fun goToRegisterScreen() {
        ViewService.setView(this, RegisterActivity())
    }

    override fun goToClientScreen() {
        ViewService.setView(this, ClientHomeActivity())
    }

    override fun goToCourierScreen() {
        ViewService.setView(this, CourierHomeActivity())
    }
}