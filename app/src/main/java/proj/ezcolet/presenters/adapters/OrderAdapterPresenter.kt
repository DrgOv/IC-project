package proj.ezcolet.presenters.adapters

import proj.ezcolet.contracts.OrdersRecyclerViewContract
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*

class OrderAdapterPresenter() : OrdersRecyclerViewContract.AdapterPresenter {
    override fun onBindVH(holder: OrderViewHolder, model: OrderModel) {
        holder.setUpperLowerTexts(
            model.orderName,
            "Status: ${model.orderStatus.toUpperCase(Locale.ROOT)}"
        )
    }
}