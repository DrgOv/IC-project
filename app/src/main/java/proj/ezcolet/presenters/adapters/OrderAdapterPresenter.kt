package proj.ezcolet.presenters.adapters

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.OrderViewHolder

abstract class OrderAdapterPresenter<T: OrderViewHolder>() {
    abstract fun onBindVH(
        holder: T,
        model: OrderModel,
        username: String
    )
}