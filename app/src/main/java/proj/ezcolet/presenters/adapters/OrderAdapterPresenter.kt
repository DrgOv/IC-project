package proj.ezcolet.presenters.adapters

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*

abstract class OrderAdapterPresenter<T: OrderViewHolder>() {
    abstract fun onBindVH(holder: T, model: OrderModel)
}