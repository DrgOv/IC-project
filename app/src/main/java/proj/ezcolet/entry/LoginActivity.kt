package proj.ezcolet.entry

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import proj.ezcolet.user.UserActivity
import proj.ezcolet.courier.CourierActivity
import proj.ezcolet.databinding.EntryLoginActivityBinding
import proj.ezcolet.services.ViewService

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)

        val binding = EntryLoginActivityBinding.inflate(layoutInflater)

        binding.registerBtn.setOnClickListener() {
            ViewService.setView(this, RegisterActivity())
        }

        binding.toUserBtn.setOnClickListener() {
            ViewService.setView(this, UserActivity())
        }

        binding.toCourierBtn.setOnClickListener() {
            ViewService.setView(this, CourierActivity())
        }

        setContentView(binding.root)
    }
}