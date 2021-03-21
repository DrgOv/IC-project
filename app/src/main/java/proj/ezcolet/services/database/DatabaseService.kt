package proj.ezcolet.services.database

import proj.ezcolet.models.Model
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel

interface DatabaseService {
    fun add(collectionName: String, document: Model)
    fun update(collectionName: String, document: Model)
    fun delete(collectionName: String, document: Model)
    suspend fun get(collectionName: String, documentId: String): Any?
    suspend fun getClient(documentId: String): ClientModel?
    suspend fun getCourier(documentId: String): CourierModel?
}