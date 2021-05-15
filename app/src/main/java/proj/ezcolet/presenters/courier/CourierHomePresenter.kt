package proj.ezcolet.presenters.courier

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import proj.ezcolet.contracts.CourierHomeContract
import proj.ezcolet.models.order.GeneralModel
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsClientService
import proj.ezcolet.services.database.FsCourierService
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.courier.CourierHomeActivity
import java.util.*

class CourierHomePresenter(courierHomeActivity: CourierHomeActivity) :
    CourierHomeContract.Presenter {
    private lateinit var courier: CourierModel
    override suspend fun initializeCourier(username: String) {
        courier = FsCourierService.getCourier(username)!!
    }

    override suspend fun updateMonth() {
        val calendar: Calendar = Calendar.getInstance()
        val current_month: String =
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("ro"))
        val month = FsOrderService.getGeneral("orderStats")
        if (month != null) {
            if (month.newMonth != current_month) {
                month.newMonth = current_month
                FsOrderService.updateGeneral(month)
                if (courier != null) {
                    courier.monthlyOrders = 0
                    FsCourierService.updateCourier(courier)
                }
            }
        }
    }


    override suspend fun getOptions(username: String): FirestoreRecyclerOptions<OrderModel> {
        val currentDay = getDate()
        val options = ViewService
            .setFsRecyclerAdapterOptions(
                FsQueryingService.getOrdersQueryWhereEqualsToDay(
                    "courierUsername", username, "orderDate", currentDay
                )
            )
        return options
    }

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

    override fun getDate(): String {
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