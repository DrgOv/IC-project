package proj.ezcolet.views.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import proj.ezcolet.contracts.OrdersRecyclerViewContract
import proj.ezcolet.presenters.viewholders.OrderViewHolderPresenter

abstract class OrderViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView),
    OrdersRecyclerViewContract.ViewHolder {
    abstract var orderViewHolderPresenter: OrderViewHolderPresenter
    abstract var upperTextView: TextView
    abstract var lowerTextView: TextView

    override fun setUpperLowerTexts(upperText: String, lowerText: String) {
        orderViewHolderPresenter.onSetUpperLowerTexts(upperText, lowerText)
    }
}