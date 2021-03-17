package proj.ezcolet.services

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity


class ViewService {
    companion object {
        fun setView(OldActivity: AppCompatActivity, NewActivity: AppCompatActivity) {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            OldActivity.startActivity(intent)
        }
    }
}