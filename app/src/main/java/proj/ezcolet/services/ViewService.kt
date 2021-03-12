package proj.ezcolet.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ViewService {
    companion object {
        fun setView(OldActivity: AppCompatActivity, NewActivity: AppCompatActivity) {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            OldActivity.startActivity(intent)
        }
    }
}