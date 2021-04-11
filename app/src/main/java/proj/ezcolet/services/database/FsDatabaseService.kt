package proj.ezcolet.services.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.order.GeneralModel
import proj.ezcolet.models.Model
import proj.ezcolet.models.order.ModelInt
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.validation.ValidationService

object FsDatabaseService : DatabaseService {
    private const val CLIENTS_COLLECTION = "clients"
    private const val COURIERS_COLLECTION = "couriers"
    private const val ORDERS_COLLECTION = "orders"
    private const val GENERAL_COLLECTION = "general"

    private fun db(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    fun getOrdersCollectionReference(): CollectionReference {
        return db().collection(ORDERS_COLLECTION)
    }
    override suspend fun add(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document).await()
    }

    override suspend fun addInt(collectionName: String, document: ModelInt) {
        db().collection(collectionName).document(document.id.toString())
            .set(document).await()
    }

    override suspend fun addClient(client: ClientModel) {
        add(CLIENTS_COLLECTION, client)
    }

    override suspend fun addCourier(courier: CourierModel) {
        add(COURIERS_COLLECTION, courier)
    }

    override suspend fun addOrder(order: OrderModel) {
        addInt(ORDERS_COLLECTION, order)
    }

    override suspend fun addGeneral(general: GeneralModel) {
        db().collection(GENERAL_COLLECTION).document("orderStats")
            .set(general).await()
    }

    override suspend fun update(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document, SetOptions.merge()).await()
    }

    override suspend fun updateInt(collectionName: String, document: ModelInt) {
        db().collection(collectionName).document(document.id.toString())
            .set(document, SetOptions.merge()).await()
    }

    override suspend fun updateClient(client: ClientModel) {
        update(CLIENTS_COLLECTION, client)
    }

    override suspend fun updateCourier(courier: CourierModel) {
        update(COURIERS_COLLECTION, courier)
    }

    override suspend fun updateOrder(order: OrderModel) {
        updateInt(ORDERS_COLLECTION, order)
    }

    override suspend fun delete(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id).delete().await()
    }

    override suspend fun deleteInt(collectionName: String, document: ModelInt) {
        db().collection(collectionName).document(document.id.toString()).delete().await()
    }

    override suspend fun deleteClient(client: ClientModel) {
        delete(CLIENTS_COLLECTION, client)
    }

    override suspend fun deleteCourier(courier: CourierModel) {
        delete(COURIERS_COLLECTION, courier)
    }

    override suspend fun deleteOrder(order: OrderModel) {
        deleteInt(ORDERS_COLLECTION, order)
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

    override suspend fun getGeneral(documentId: String): GeneralModel? {
        return get(GENERAL_COLLECTION, documentId)?.toObject<GeneralModel>()
    }

    suspend fun getUserByUsername(username: String): UserModel? {
        return if (ValidationService.hasCourierUsername(username)) {
            getCourier(username)
        } else {
            getClient(username)
        }
    }

    suspend fun getOrdersByUsername(username: String): List<OrderModel> {
        return db().collection(ORDERS_COLLECTION)
            .whereEqualTo("clientUsername", username)
            .get().await().toObjects()
    }

    suspend fun getClientBasedOnNamePhone(
        firstName: String,
        lastName: String,
        phone: String
    ): ClientModel? {

        var client: ClientModel? = null
        db().collection(CLIENTS_COLLECTION).whereEqualTo("firstName", firstName)
            .whereEqualTo("lastName", lastName).whereEqualTo("phone", phone).get()
            .addOnSuccessListener { querySnapshot ->
                for (query in querySnapshot)
                    client = query.toObject()

            }.await()

        return client


    }

}