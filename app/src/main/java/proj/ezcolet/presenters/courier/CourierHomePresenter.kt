package proj.ezcolet.presenters.courier

import proj.ezcolet.contracts.CourierHomeContract
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.views.courier.CourierHomeActivity

class CourierHomePresenter(courierHomeActivity: CourierHomeActivity) :
    CourierHomeContract.Presenter {

    override suspend fun updateOrders(
        orderList: MutableList<OrderModel>,
        fromPosition: Int,
        toPosition: Int
    ) {
        var aux = orderList.get(fromPosition).id
        orderList.get(fromPosition).id = orderList.get(toPosition).id
        orderList.get(toPosition).id = aux

        println(orderList.get(fromPosition).id)
        println(orderList.get(toPosition).id)

        FsOrderService.addOrder(orderList.get(fromPosition))
        FsOrderService.addOrder(orderList.get(toPosition))

    }

}