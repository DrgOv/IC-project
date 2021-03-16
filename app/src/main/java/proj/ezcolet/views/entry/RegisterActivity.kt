package proj.ezcolet.views.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import proj.ezcolet.databinding.EntryRegisterActivityBinding
import proj.ezcolet.models.ClientModel
import proj.ezcolet.presenters.entry.RegisterPresenter


class RegisterActivity : AppCompatActivity() {
    private val registerPresenter = RegisterPresenter(this)
    private lateinit var binding: EntryRegisterActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntryRegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener() { addClient() }
    }

    private fun addClient() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val street = binding.streetEditText.text.toString()
        val county = binding.countyEditText.text.toString()
        val city = binding.cityEditText.text.toString()
        val zipCode = binding.zipCodeEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val username = binding.userEditText.text.toString()
        val password = binding.passEditText.text.toString()

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

        registerPresenter.addClient(newClient)
    }
}