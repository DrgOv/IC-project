package proj.ezcolet.services.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import proj.ezcolet.models.Model
import proj.ezcolet.models.users.ClientModel

object FsClientService : FsDatabaseService() {
    private const val CLIENTS_COLLECTION = "clients"

    fun getCollectionRef(): CollectionReference {
        return super.db().collection(CLIENTS_COLLECTION)
    }

    suspend fun addClient(client: ClientModel) {
        super.add(CLIENTS_COLLECTION, client)
    }

    suspend fun updateClient(client: ClientModel) {
        super.update(CLIENTS_COLLECTION, client)
    }

    suspend fun deleteClient(client: ClientModel) {
        super.delete(CLIENTS_COLLECTION, client)
    }

    suspend fun getClient(id: String): ClientModel? {
        return super.get(CLIENTS_COLLECTION, id)?.toObject()
    }
}