package proj.ezcolet.views.entry

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.contracts.RegisterContract
import proj.ezcolet.databinding.EntryRegisterActivityBinding
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.presenters.entry.RegisterPresenter
import proj.ezcolet.services.ViewService
import kotlin.coroutines.CoroutineContext


class RegisterActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(),
    RegisterContract.View, CoroutineScope {
    private lateinit var registerPresenter: RegisterContract.Presenter
    private lateinit var binding: EntryRegisterActivityBinding

    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var streetET: EditText
    private lateinit var countyET: EditText
    private lateinit var cityET: EditText
    private lateinit var zipCodeET: EditText
    private lateinit var phoneET: EditText
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)
        binding = EntryRegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerPresenter = RegisterPresenter(this)
        bindViews()
        setListeners()
    }

    private fun bindViews() {
        firstNameET = binding.firstNameEditText
        lastNameET = binding.lastNameEditText
        streetET = binding.streetEditText
        countyET = binding.countyEditText
        cityET = binding.cityEditText
        zipCodeET = binding.zipCodeEditText
        phoneET = binding.phoneEditText
        usernameET = binding.userEditText
        passwordET = binding.passEditText
        registerBtn = binding.registerBtn
    }

    private fun setListeners() {
        registerBtn.setOnClickListener() {
            launch {
                if (addClient()) {
                    println("REGISTERED SUCCESSFULLY")
                    goToLoginScreen()
                }
            }
        }
    }

    private suspend fun addClient(): Boolean {
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val street = streetET.text.toString()
        val county = countyET.text.toString()
        val city = cityET.text.toString()
        val zipCode = zipCodeET.text.toString()
        val phone = phoneET.text.toString()
        val username = usernameET.text.toString()
        val password = passwordET.text.toString()

        val newClient = ClientModel(
            id = username,
            lastName = lastName,
            firstName = firstName,
            street = street,
            county = county,
            city = city,
            zipCode = zipCode,
            phone = phone,
            username = username,
            password = password
        )

        return registerPresenter.addClient(newClient)
    }

    override fun showLastNameError(error: String) {
        lastNameET.error = error
    }

    override fun showFirstNameError(error: String) {
        firstNameET.error = error
    }

    override fun showStreetError(error: String) {
        streetET.error = error
    }

    override fun showCountyError(error: String) {
        countyET.error = error
    }

    override fun showCityError(error: String) {
        cityET.error = error
    }

    override fun showZipCodeError(error: String) {
        zipCodeET.error = error
    }

    override fun showPhoneError(error: String) {
        phoneET.error = error
    }

    override fun showUsernameError(error: String) {
        usernameET.error = error
    }

    override fun showPasswordError(error: String) {
        passwordET.error = error
    }

    override fun goToLoginScreen() {
        ViewService.setView(this, LoginActivity())
    }
}