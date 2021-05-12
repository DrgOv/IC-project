package proj.ezcolet.contracts

import proj.ezcolet.models.order.OrderModel

interface CourierHomeContract {
    interface Presenter {
        suspend fun updateOrders(
            orderList: MutableList<OrderModel>,
            fromPosition: Int,
            toPosition: Int
        )
    }
}