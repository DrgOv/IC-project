package proj.ezcolet.services.database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.Model
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel
import java.lang.NullPointerException

class FsDatabaseService : DatabaseService {
    private val db = Firebase.firestore

    companion object {
        const val CLIENTS_COLLECTION = "clients"
        const val COURIERS_COLLECTION = "couriers"
        const val ORDERS_COLLECTION = "orders"
    }

    override fun add(collectionName: String, document: Model) {
        db.collection(collectionName).document(document.id).set(document)
    }

    override fun update(collectionName: String, document: Model) {
        db.collection(collectionName).document(document.id).set(document, SetOptions.merge())
    }

    override fun delete(collectionName: String, document: Model) {
        db.collection(collectionName).document(document.id).delete()
    }

    override suspend fun get(collectionName: String, documentId: String): DocumentSnapshot? {
        var docSnapshot: DocumentSnapshot? = null
        docSnapshot = db.collection(collectionName).document(documentId).get().await()
        println(docSnapshot.data)
        return docSnapshot
    }

    override suspend fun getClient(documentId: String): ClientModel? {
        return get(CLIENTS_COLLECTION, documentId)?.toObject<ClientModel>()
    }

    override suspend fun getCourier(documentId: String): CourierModel? {
        return get(COURIERS_COLLECTION, documentId)?.toObject<CourierModel>()
    }
}