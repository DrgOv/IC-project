package proj.ezcolet.contracts

import proj.ezcolet.models.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.UserModel

interface CourierQrScanContract {
    interface Presenter {
        suspend fun addOrder(newOrder: OrderModel)
        suspend fun addOrderInfo(courierUsername:String)
        fun splitOrderInfos(info: String)

    }
}