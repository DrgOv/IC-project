package proj.ezcolet.views.courier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.courier_info_activity.*
import proj.ezcolet.R
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.databinding.CourierInfoActivityBinding

class CourierInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courier_info_activity)
        val binding = CourierInfoActivityBinding.inflate(layoutInflater)
        val username = intent.getStringExtra("Username")
        courierUsernameTextView.text = username
        println(username)
    }
}