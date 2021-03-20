package proj.ezcolet.services.database

import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import proj.ezcolet.models.Model
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

    override fun get(collectionName: String, documentId: String): Model? {
        var user: Model? = null ?: throw NullPointerException()
        val docRef = db.collection(collectionName).document(documentId)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot != null) {
                user = documentSnapshot.toObject<Model>()
                println("${documentSnapshot.data}")
            } else {
                println("No document")
                user = null
            }
        }
        return user
    }
}