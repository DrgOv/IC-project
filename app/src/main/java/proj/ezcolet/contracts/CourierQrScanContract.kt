package proj.ezcolet.contracts

import proj.ezcolet.models.order.OrderModel

interface CourierQrScanContract {
    interface Presenter {
        suspend fun addOrder(newOrder: OrderModel)
        suspend fun addOrderInfo(courierUsername:String)
        fun splitOrderInfos(info: String)

    }
}