package proj.ezcolet.presenters.courier

import proj.ezcolet.contracts.CourierQrScanContract
import proj.ezcolet.models.order.GeneralModel
import proj.ezcolet.models.order.NORMAL
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.courier.CourierQrScanActivity
import java.util.*
import kotlin.properties.Delegates

private lateinit var orderDetails: List<String>
private lateinit var orderName: String
private lateinit var clientFirstName: String
private lateinit var clientLastName: String
private lateinit var clientPhone: String
private lateinit var clientStreet: String
private lateinit var clientCity: String
private lateinit var clientCounty: String
private lateinit var orderSum: String
private lateinit var clientUsername: String
private lateinit var orderDate: String

private var orderNumber by Delegates.notNull<Int>()
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
        if (orderDetails.size == 16) {
            println(orderDetails[1])
            println(orderDetails[3])
            println(orderDetails[5])
            println(orderDetails[7])
            println(orderDetails[9])
            println(orderDetails[11])
            println(orderDetails[13])
            println(orderDetails[15])


            orderName = orderDetails[1]
            clientLastName = orderDetails[3]
            clientFirstName = orderDetails[5]
            clientPhone = orderDetails[7]
            clientStreet = orderDetails[9]
            clientCity = orderDetails[11]
            clientCounty = orderDetails[13]
            orderSum = orderDetails[15]
        }


    }

    override fun checkIfValid(): Int {
        if (orderDetails.size == 16)
            return 0;
        return 1;
    }

    override suspend fun addOrderInfo(courierUsername: String) {
        clientUsername = checkClient()
        orderNumber = getOrderNumber()
        orderDate = getDate()
        var newOrder = OrderModel(
            courierUsername,
            clientUsername,
            orderDetails[1],
            "",
            orderDetails[3],
            orderDetails[5],
            orderDetails[7],
            orderDetails[9],
            orderDetails[11],
            orderDetails[13],
            orderDetails[15],
            orderNumber,
            NORMAL,
            orderDate
        )
        addOrder(newOrder)
    }

    private suspend fun checkClient(): String {
        val clientObj = FsQueryingService.getClientBasedOnNamePhone(
            clientFirstName,
            clientLastName,
            clientPhone
        )
        if (clientObj != null)
            return clientObj.username
        else
            return ""

    }

    private suspend fun getOrderNumber(): Int {
        val orderNum = FsOrderService.getGeneral("orderStats")
        if (orderNum != null) {
            return orderNum.orderNumber
        }
        return 0;
    }


    override suspend fun addOrder(newOrder: OrderModel) {

        FsOrderService.addOrder(newOrder)
        orderNumber += 1
        var newGeneral = GeneralModel(orderNumber)
        FsOrderService.addGeneral(newGeneral)
    }

    private fun getDate(): String {
        val calendar: Calendar = Calendar.getInstance()
        val month: String =
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("ro"))

        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfMonthStr = dayOfMonth.toString()

        val year: Int = calendar.get(Calendar.YEAR)
        val yearStr = year.toString()

        val date = "$dayOfMonthStr $month $yearStr"
        return date

    }
}