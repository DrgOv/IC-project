package proj.ezcolet.services.database

import com.google.firebase.firestore.*
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

abstract class FsDatabaseService : DatabaseService {
    protected fun db(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    override suspend fun add(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document).await()
    }

    override suspend fun update(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id)
            .set(document, SetOptions.merge()).await()
    }

    override suspend fun delete(collectionName: String, document: Model) {
        db().collection(collectionName).document(document.id).delete().await()
    }

    override suspend fun get(collectionName: String, documentId: String): DocumentSnapshot? {
        return db().collection(collectionName).document(documentId).get()
            .await()
    }



}