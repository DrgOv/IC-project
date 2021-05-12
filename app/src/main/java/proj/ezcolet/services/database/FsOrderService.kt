package proj.ezcolet.services.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.order.GeneralModel
import proj.ezcolet.models.order.ModelInt
import proj.ezcolet.models.order.OrderModel

object FsOrderService : FsDatabaseService() {
    private const val ORDERS_COLLECTION = "orders"
    private const val GENERAL_COLLECTION = "general"

    fun getCollectionRef(): CollectionReference {
        return super.db().collection(ORDERS_COLLECTION)
    }

    suspend fun addInt(collectionName: String, document: ModelInt) {
        super.db().collection(collectionName).document(document.id.toString()).set(document).await()
    }

    suspend fun addOrder(order: OrderModel) {
        addInt(ORDERS_COLLECTION, order)
    }

    suspend fun addGeneral(generalModel: GeneralModel) {
        super.db().collection(GENERAL_COLLECTION).document("orderStats").set(generalModel).await()
    }

    suspend fun updateInt(collectionName: String, document: ModelInt) {
        db().collection(collectionName).document(document.id.toString())
            .set(document, SetOptions.merge()).await()
    }

    suspend fun updateOrder(order: OrderModel) {
        updateInt(ORDERS_COLLECTION, order)
    }

    suspend fun deleteInt(collectionName: String, document: ModelInt) {
        super.db().collection(collectionName).document(document.id.toString()).delete().await()
    }

    suspend fun deleteOrder(order: OrderModel) {
        deleteInt(ORDERS_COLLECTION, order)
    }

    suspend fun getGeneral(id: String): GeneralModel? {
        return super.get(GENERAL_COLLECTION, id)?.toObject()
    }
}