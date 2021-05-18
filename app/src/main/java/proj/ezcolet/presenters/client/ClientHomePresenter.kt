package proj.ezcolet.presenters.client

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    suspend fun initializeClient() {
        client = FsClientService.getClient(clientHomeActivity.intent.getStringExtra("Username")!!)!!
    }

    private suspend fun getOptions(): FirestoreRecyclerOptions<OrderModel> {
        val currentDay = getDate()
        val order = client.getOrdersByUsername()[0]
        return ViewService.setFsRecyclerAdapterOptions(
            FsQueryingService.getOrdersQueryWhereEqualsToDay(
                "courierUsername", order.courierUsername, "orderDate", currentDay
            )
        )
    }

    suspend fun setUpAdapter() {
        clientHomeActivity.setUpAdapter(client, getOptions())
    }

    suspend fun setUpTexts() {
        clientHomeActivity.setWelcomeText("Buna ziua, ${client.firstName}")
        courier = FsCourierService.getCourier(client.getOrdersByUsername()[0].courierUsername)!!
        clientHomeActivity.setCourierUsernameText("Curier: ${courier.firstName} ${courier.lastName}")
        clientHomeActivity.setLikes(courier.likes)
        clientHomeActivity.setDislikes(courier.dislikes)
    }

    suspend fun addLike() {
        if (!client.gaveRating) {
            courier.addLike()
            clientHomeActivity.setLikes(courier.likes)
            client.hasRated()
        }
        if (client.gaveRating) {
            clientHomeActivity.showInvalidRatingToast()
        }
    }

    suspend fun addDislike() {
        if (!client.gaveRating) {
            courier.addDislike()
            clientHomeActivity.setDislikes(courier.dislikes)
            client.hasRated()
        }
        if (client.gaveRating) {
            clientHomeActivity.showInvalidRatingToast()
        }
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