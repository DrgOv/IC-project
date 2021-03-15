package proj.ezcolet.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import proj.ezcolet.models.OrderModel
import proj.ezcolet.R

class CourierAdapter(private val list: List<OrderModel>) : RecyclerView.Adapter<CourierAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.courier_card_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView1.text = currentItem.orderName
        holder.textView2.text = currentItem.orderDetails
    }

    override fun getItemCount() = list.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.courierOrderNameTextView)
        val textView2: TextView = itemView.findViewById(R.id.courierOrderDetailsTextView)
    }

}