package proj.ezcolet.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import proj.ezcolet.models.OrderModel
import proj.ezcolet.R

class UserAdapter(private val list: List<OrderModel>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.client_card_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount() = list.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_view_card1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_card2)
    }
}