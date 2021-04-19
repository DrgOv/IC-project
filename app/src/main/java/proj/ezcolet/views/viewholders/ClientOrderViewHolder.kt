package proj.ezcolet.views.viewholders

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import proj.ezcolet.databinding.ClientCardItemBinding

class ClientOrderViewHolder(itemView: View) :
    OrderViewHolder(itemView) {
    private var binding: ClientCardItemBinding = ClientCardItemBinding.bind(itemView)
    override var upperTextView: TextView = binding.upperTextView
    override var lowerTextView: TextView = binding.lowerTextView
    val deliverImageBtn: ImageButton = binding.deliverBtn

    fun hideDeliverBtn() {
        deliverImageBtn.visibility = View.GONE
    }
}