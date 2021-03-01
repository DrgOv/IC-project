package proj.ezcolet.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import proj.ezcolet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameET.text
            val password = binding.passwordET.text
            Toast.makeText(applicationContext, "User: ${username}, Password: ${password}", Toast.LENGTH_SHORT).show()
        }
        setContentView(binding.root)
    }
}