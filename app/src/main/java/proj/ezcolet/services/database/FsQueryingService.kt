package proj.ezcolet.services.database

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.validation.ValidationService

object FsQueryingService : FsDatabaseService() {
    suspend fun getUserByUsername(username: String): UserModel? {
        return if (ValidationService.hasCourierUsername(username)) {
            FsCourierService.getCourier(username)
        } else {
            FsClientService.getClient(username)
        }
    }

    suspend fun getOrdersByClientUsername(username: String): List<OrderModel> {
        return FsOrderService.getCollectionRef()
            .whereEqualTo("clientUsername", username)
            .get().await().toObjects()
    }

    fun getOrdersQueryWhereEqualsTo(fieldName: String, fieldValue: String): Query {
        return FsOrderService.getCollectionRef().whereEqualTo(fieldName, fieldValue)
    }

    suspend fun getClientBasedOnNamePhone(
        firstName: String,
        lastName: String,
        phone: String
    ): ClientModel? {
        var client: ClientModel? = null
        FsClientService.getCollectionRef().whereEqualTo("firstName", firstName)
            .whereEqualTo("lastName", lastName).whereEqualTo("phone", phone).get()
            .addOnSuccessListener { querySnapshot ->
                for (query in querySnapshot)
                    client = query.toObject()
            }.await()

        return client
    }


    suspend fun getOrdersBasedOnCourierUserName(
        courierUsername: String
    ): MutableList<OrderModel> {
        lateinit var order: OrderModel
        var orderList: MutableList<OrderModel> = mutableListOf()
        FsOrderService.getCollectionRef().whereEqualTo("courierUsername", courierUsername).get()
            .addOnSuccessListener { querySnapshot ->
                for (query in querySnapshot) {
                    order = query.toObject(OrderModel::class.java)
                    orderList.add(order)


                }
            }.await()

        return orderList
    }
}