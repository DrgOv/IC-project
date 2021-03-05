package proj.ezcolet.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import proj.ezcolet.Register

class ViewService {
    companion object {
        fun setView(OldActivity: AppCompatActivity, NewActivity: Register) {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            OldActivity.startActivity(intent)
        }
    }
}