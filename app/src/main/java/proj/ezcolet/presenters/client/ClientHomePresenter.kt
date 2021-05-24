package proj.ezcolet.presenters.client

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import proj.ezcolet.models.order.COMPLETED
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsClientService
import proj.ezcolet.services.database.FsCourierService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.client.ClientHomeActivity
import java.util.*

class ClientHomePresenter(private val clientHomeActivity: ClientHomeActivity) {
    private lateinit var client: ClientModel
    private lateinit var courier: CourierModel
    private lateinit var courierUsername: String

    suspend fun initializeClient() {
        client = FsClientService.getClient(clientHomeActivity.intent.getStringExtra("Username")!!)!!
    }

    private suspend fun getOptions(): FirestoreRecyclerOptions<OrderModel> {
        val currentDay = getDate()
        lateinit var option: FirestoreRecyclerOptions<OrderModel>
        var orderList =
            FsQueryingService.getOrderBasedOnClientUserNameAndDate(client.username, currentDay)
        if (orderList.size != 0) {
            println("here")
            val order = orderList[0]
            courierUsername = order.courierUsername
            option = ViewService.setFsRecyclerAdapterOptions(
                FsQueryingService.getOrdersQueryWhereEqualsToDay(
                    "courierUsername", order.courierUsername, "orderDate", currentDay
                )
            )

        } else {
            option = ViewService.setFsRecyclerAdapterOptions(
                FsQueryingService.getOrdersQueryWhereEqualsToDay(
                    "courierUsername", "", "orderDate", currentDay
                )
            )
            courierUsername = "curier_"
        }
        return option
    }

    suspend fun setUpAdapter() {
        clientHomeActivity.setUpAdapter(client, getOptions())
    }

    suspend fun setUpTexts() {
        clientHomeActivity.setWelcomeText("Bună ziua, ${client.firstName}")
        courier = FsCourierService.getCourier(courierUsername)!!
        clientHomeActivity.setCourierUsernameText("Curier: ${courier.firstName} ${courier.lastName}")
        clientHomeActivity.setLikes(courier.likes)
        clientHomeActivity.setDislikes(courier.dislikes)
    }

    suspend fun addLike() {
        val currentDay = getDate()
        var orderList =
            FsQueryingService.getOrderBasedOnClientUserNameAndDate(client.username, currentDay)
        if (orderList.size != 0) {
            val order = orderList[0]
            if (order.orderStatus == COMPLETED) {
                if (!client.gaveRating) {
                    courier.addLike()
                    clientHomeActivity.setLikes(courier.likes)
                    client.hasRated()
                    return
                }
                if (client.gaveRating) {
                    clientHomeActivity.showInvalidRatingToast()
                    return
                }
            }
        }
        clientHomeActivity.showCantRateToast()
    }

    suspend fun addDislike() {
        val currentDay = getDate()
        var orderList =
            FsQueryingService.getOrderBasedOnClientUserNameAndDate(client.username, currentDay)
        if (orderList.size != 0) {
            val order = orderList[0]
            if (order.orderStatus == COMPLETED) {
                if (!client.gaveRating) {
                    courier.addDislike()
                    clientHomeActivity.setDislikes(courier.dislikes)
                    client.hasRated()
                    return
                }
                if (client.gaveRating) {
                    clientHomeActivity.showInvalidRatingToast()
                    return
                }
            }
        }
        clientHomeActivity.showCantRateToast()
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