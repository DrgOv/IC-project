package proj.ezcolet.presenters.viewholders

import proj.ezcolet.contracts.OrdersRecyclerViewContract
import proj.ezcolet.views.viewholders.OrderViewHolder

class OrderViewHolderPresenter(private val orderViewHolder: OrderViewHolder) :
    OrdersRecyclerViewContract.ViewHolderPresenter {
    override fun onSetUpperLowerTexts(upperText: String, lowerText: String) {
        orderViewHolder.upperTextView.text = upperText
        orderViewHolder.lowerTextView.text = lowerText
    }
}