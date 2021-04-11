package proj.ezcolet.presenters.adapters

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.viewholders.OrderViewHolder

class CourierOrderAdapterPresenter(orderAdapter: OrderAdapter) :
    OrderAdapterPresenter(orderAdapter) {
    override fun onBindVH(holder: OrderViewHolder, model: OrderModel) {
        TODO("Not yet implemented")
    }
}