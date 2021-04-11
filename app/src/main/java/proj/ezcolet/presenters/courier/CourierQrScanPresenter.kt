package proj.ezcolet.presenters.courier

import proj.ezcolet.contracts.CourierQrScanContract
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.views.courier.CourierQrScanActivity

private lateinit var orderDetails: List<String>
val delimiter1 = "\n"
val delimiter2 = "Comanda: "
val delimiter3 = "Nume: "
val delimiter4 = "Prenume: "
val delimiter5 = "Telefon: "
val delimiter6 = "Strada: "
val delimiter7 = "Oraș: "
val delimiter8 = "Județ: "
val delimiter9 = "Suma: "

class CourierQrScanPresenter(courierQrScanActivity: CourierQrScanActivity) :
    CourierQrScanContract.Presenter {
    override fun splitOrderInfos(order: String) {
        orderDetails = order.split(
            delimiter1,
            delimiter2,
            delimiter3,
            delimiter4,
            delimiter5,
            delimiter6,
            delimiter7,
            delimiter8,
            delimiter9
        )
        println(orderDetails[1])
        println(orderDetails[3])
        println(orderDetails[5])
        println(orderDetails[7])
        println(orderDetails[9])
        println(orderDetails[11])
        println(orderDetails[13])
        println(orderDetails[15])


    }

    override suspend fun addOrderInfo() {
        var newOrder = OrderModel(
            "alex",
            "anda.herz",
            orderDetails[1],
            "13",
            orderDetails[3],
            orderDetails[5],
            orderDetails[7],
            orderDetails[9],
            orderDetails[11],
            orderDetails[13],
            orderDetails[15]
        )
        addOrder(newOrder)
    }

    override suspend fun addOrder(newOrder: OrderModel) {

        FsDatabaseService.addOrder(newOrder)

    }
}