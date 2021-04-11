package proj.ezcolet.views.viewholders

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import proj.ezcolet.databinding.ClientCardItemBinding
import proj.ezcolet.presenters.viewholders.ClientOrderViewHolderPresenter
import proj.ezcolet.presenters.viewholders.OrderViewHolderPresenter

class ClientOrderViewHolder(itemView: View) :
    OrderViewHolder(itemView) {
    override var orderViewHolderPresenter: OrderViewHolderPresenter =
        ClientOrderViewHolderPresenter(this)
    private var binding: ClientCardItemBinding = ClientCardItemBinding.bind(itemView)
    override var upperTextView: TextView = binding.upperTextView
    override var lowerTextView: TextView = binding.lowerTextView
}