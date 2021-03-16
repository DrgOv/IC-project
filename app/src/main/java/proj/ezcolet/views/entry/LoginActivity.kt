package proj.ezcolet.views.entry

import android.content.Intent
import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import proj.ezcolet.databinding.EntryLoginActivityBinding
import proj.ezcolet.services.ViewService
import proj.ezcolet.views.client.ClientActivity
import proj.ezcolet.views.courier.CourierHomeActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)

        val binding = EntryLoginActivityBinding.inflate(layoutInflater)

        binding.loginBtn.setOnClickListener() {
        }

        binding.toRegisterBtn.setOnClickListener() {
            ViewService.setView(this, RegisterActivity())
        }

        binding.toUserBtn.setOnClickListener() {
            ViewService.setView(this, ClientActivity())
        }

        binding.toCourierBtn.setOnClickListener() {
            ViewService.setView(this, CourierHomeActivity())
        }

        setContentView(binding.root)
    }
}