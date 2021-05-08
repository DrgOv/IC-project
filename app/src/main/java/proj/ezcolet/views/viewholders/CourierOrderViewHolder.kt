package proj.ezcolet.views.viewholders

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import proj.ezcolet.databinding.CourierCardItemBinding

class CourierOrderViewHolder(itemView: View) :
    OrderViewHolder(itemView) {
    private var binding: CourierCardItemBinding = CourierCardItemBinding.bind(itemView)
    override var upperTextView: TextView = binding.courierOrderNameTextView
    override var lowerTextView: TextView = binding.courierOrderDetailsTextView
    val checkImageBtn: ImageButton = binding.checkBtn
    val cancelImageBtn: ImageButton = binding.cancelBtn
    init {
        itemView.setOnClickListener { v: View ->
            val position: Int = absoluteAdapterPosition
            Toast.makeText(
                itemView.context,
                "You clicked on item ${position + 1}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}