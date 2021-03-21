package proj.ezcolet.services.database

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.Model
import proj.ezcolet.models.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel

object FsDatabaseService : DatabaseService {
    private const val CLIENTS_COLLECTION = "clients"
    private const val COURIERS_COLLECTION = "couriers"
    private const val ORDERS_COLLECTION = "orders"

    private fun db(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    override fun add(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document)
    }

    override fun addClient(client: ClientModel) {
        add(CLIENTS_COLLECTION, client)
    }

    override fun addCourier(courier: CourierModel) {
        add(COURIERS_COLLECTION, courier)
    }

    override fun addOrder(order: OrderModel) {
        add(ORDERS_COLLECTION, order)
    }

    override fun update(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document, SetOptions.merge())
    }

    override fun delete(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id).delete()
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
}