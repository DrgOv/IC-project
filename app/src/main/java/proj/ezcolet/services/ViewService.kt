package proj.ezcolet.services

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class ViewService {
    companion object {
        fun setView(OldActivity: AppCompatActivity, NewActivity: AppCompatActivity) {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            OldActivity.startActivity(intent)
        }

        fun setViewAndId(OldActivity: AppCompatActivity, NewActivity: AppCompatActivity,username:String)
        {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            intent.putExtra("Username", username)
            OldActivity.startActivity(intent)

        }

        fun setEditTextError(editText: EditText, error: String) {
            editText.error = error
        }
    }
}