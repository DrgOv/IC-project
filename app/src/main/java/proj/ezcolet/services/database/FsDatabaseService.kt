package proj.ezcolet.services.database

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import proj.ezcolet.models.Model

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