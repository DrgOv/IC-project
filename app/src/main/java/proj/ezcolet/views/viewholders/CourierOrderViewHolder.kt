package proj.ezcolet.views.viewholders

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import proj.ezcolet.databinding.ClientCardItemBinding
import proj.ezcolet.databinding.CourierCardItemBinding
import proj.ezcolet.presenters.viewholders.ClientOrderViewHolderPresenter
import proj.ezcolet.presenters.viewholders.CourierOrderViewHolderPresenter
import proj.ezcolet.presenters.viewholders.OrderViewHolderPresenter

class CourierOrderViewHolder(itemView: View) :
    OrderViewHolder(itemView) {
    override var orderViewHolderPresenter: OrderViewHolderPresenter =
        CourierOrderViewHolderPresenter(this)
    private var binding: CourierCardItemBinding = CourierCardItemBinding.bind(itemView)
    override var upperTextView: TextView = binding.courierOrderNameTextView
    override var lowerTextView: TextView = binding.courierOrderDetailsTextView
}