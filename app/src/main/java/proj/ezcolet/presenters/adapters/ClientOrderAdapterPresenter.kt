package proj.ezcolet.presenters.adapters

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*

class ClientOrderAdapterPresenter(orderAdapter: OrderAdapter) :
    OrderAdapterPresenter(orderAdapter) {
    override fun onBindVH(holder: OrderViewHolder, model: OrderModel) {
        holder.setUpperLowerTexts(model.orderName, "Status: ${model.orderStatus.toUpperCase(Locale.ROOT)}")
    }
}