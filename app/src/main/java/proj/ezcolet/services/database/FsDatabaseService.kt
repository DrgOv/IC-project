package proj.ezcolet.services.database

import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.Model
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.validation.ValidationService

object FsDatabaseService : DatabaseService {
    private const val CLIENTS_COLLECTION = "clients"
    private const val COURIERS_COLLECTION = "couriers"
    private const val ORDERS_COLLECTION = "orders"

    private fun db(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    private fun getCollectionReference(collectionName: String): CollectionReference {
        return db().collection(collectionName)
    }

    fun getClientsCollectionReference(): CollectionReference {
        return getCollectionReference(CLIENTS_COLLECTION)
    }

    fun getCouriersCollectionReference(): CollectionReference {
        return getCollectionReference(COURIERS_COLLECTION)
    }

    fun getOrdersCollectionReference(): CollectionReference {
        return getCollectionReference(ORDERS_COLLECTION)
    }

    override suspend fun add(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document).await()
    }

    override suspend fun addClient(client: ClientModel) {
        add(CLIENTS_COLLECTION, client)
    }

    override suspend fun addCourier(courier: CourierModel) {
        add(COURIERS_COLLECTION, courier)
    }

    override suspend fun addOrder(order: OrderModel) {
        add(ORDERS_COLLECTION, order)
    }

    override suspend fun update(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document, SetOptions.merge()).await()
    }

    override suspend fun updateClient(client: ClientModel) {
        update(CLIENTS_COLLECTION, client)
    }

    override suspend fun updateCourier(courier: CourierModel) {
        update(COURIERS_COLLECTION, courier)
    }

    override suspend fun updateOrder(order: OrderModel) {
        update(ORDERS_COLLECTION, order)
    }

    override suspend fun delete(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id).delete().await()
    }

    override suspend fun deleteClient(client: ClientModel) {
        delete(CLIENTS_COLLECTION, client)
    }

    override suspend fun deleteCourier(courier: CourierModel) {
        delete(COURIERS_COLLECTION, courier)
    }

    override suspend fun deleteOrder(order: OrderModel) {
        delete(ORDERS_COLLECTION, order)
    }

    override suspend fun get(collectionName: String, documentId: String): DocumentSnapshot? {
        return db().collection(collectionName).document(documentId).get()
            .await()
    }

    override suspend fun getClient(documentId: String): ClientModel? {
        return get(CLIENTS_COLLECTION, documentId)?.toObject<ClientModel>()
    }

    override suspend fun getCourier(documentId: String): CourierModel? {
        return get(COURIERS_COLLECTION, documentId)?.toObject<CourierModel>()
    }

    override suspend fun getOrder(documentId: String): OrderModel? {
        return get(ORDERS_COLLECTION, documentId)?.toObject<OrderModel>()
    }

    suspend fun getUserBasedOnUsername(username: String): UserModel? {
        return if (ValidationService.hasCourierUsername(username)) {
            getCourier(username)
        } else {
            getClient(username)
        }
    }

    suspend fun getAllOrders(): List<OrderModel> {
        val orders: MutableList<OrderModel> = arrayListOf()
        val querySnapshot = db().collection(ORDERS_COLLECTION).get().await()
        for (document in querySnapshot) {
            val order = document.toObject<OrderModel>()
            orders.add(order)
        }
        println("Orders ${orders.size}")
        return orders
    }
}