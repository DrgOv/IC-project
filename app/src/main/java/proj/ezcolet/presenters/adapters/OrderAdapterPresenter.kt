package proj.ezcolet.presenters.adapters

import proj.ezcolet.contracts.OrdersRecyclerViewContract
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*

abstract class OrderAdapterPresenter() : OrdersRecyclerViewContract.AdapterPresenter {
    override fun onBindVH(holder: OrderViewHolder, model: OrderModel) {
        holder.setUpperLowerTexts(
            model.orderName,
            "livrat la ora: ${model.orderDetails.toUpperCase(Locale.ROOT)}"
        )
    }

    override fun onBindVHCourier(holder: OrderViewHolder, model: OrderModel) {
        holder.setUpperLowerTexts(
            model.orderName,
            "status: ${model.orderStatus.toLowerCase(Locale.ROOT)}"
        )
    }
}