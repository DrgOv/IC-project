package proj.ezcolet.views.admin

import android.graphics.PixelFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.AdminAddCourierActivityBinding
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.presenters.admin.AdminAddCourierPresenter
import kotlin.coroutines.CoroutineContext

class AdminAddCourierActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) : AppCompatActivity(), CoroutineScope {
    private lateinit var adminAddCourierPresenter: AdminAddCourierPresenter
    private lateinit var binding: AdminAddCourierActivityBinding

    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var countyET: EditText
    private lateinit var cityET: EditText
    private lateinit var phoneET: EditText
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var saveCourierBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)
        adminAddCourierPresenter = AdminAddCourierPresenter()
        binding = AdminAddCourierActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstNameET = binding.firstNameEditText
        lastNameET = binding.lastNameEditText
        countyET = binding.countyEditText
        cityET = binding.cityEditText
        phoneET = binding.phoneEditText
        usernameET = binding.userEditText
        passwordET = binding.passEditText
        saveCourierBtn = binding.saveCourierBtn

        saveCourierBtn.setOnClickListener() {
            launch {
                addCourier()
                println("REGISTERED SUCCESSFULLY")
            }
        }
    }

    private suspend fun addCourier() {
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val county = countyET.text.toString()
        val city = cityET.text.toString()
        val phone = phoneET.text.toString()
        val username = usernameET.text.toString()
        val password = passwordET.text.toString()

        val newCourier = CourierModel(
            id = username,
            firstName = firstName,
            lastName = lastName,
            county = county,
            city = city,
            phone = phone,
            username = username,
            password = password
        )

        adminAddCourierPresenter.addCourier(newCourier)

        firstNameET.text.clear()
        lastNameET.text.clear()
        countyET.text.clear()
        cityET.text.clear()
        phoneET.text.clear()
        usernameET.text.clear()
        passwordET.text.clear()
    }
}