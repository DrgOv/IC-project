package proj.ezcolet.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import proj.ezcolet.models.order.OrderModel


class ViewService {
    companion object {
        fun setView(OldActivity: AppCompatActivity, NewActivity: AppCompatActivity) {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            OldActivity.startActivity(intent)
        }

        fun setViewAndId(OldActivity: AppCompatActivity, NewActivity: AppCompatActivity, username:String)
        {
            val intent = Intent(OldActivity.applicationContext, NewActivity::class.java)
            intent.putExtra("Username", username)
            OldActivity.startActivity(intent)
        }

        fun setFsRecyclerAdapterOptions(query: Query): FirestoreRecyclerOptions<OrderModel> {
            return FirestoreRecyclerOptions.Builder<OrderModel>().setQuery(query, OrderModel::class.java).build()
        }
    }
}