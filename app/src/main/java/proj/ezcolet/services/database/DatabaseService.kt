package proj.ezcolet.services.database

import proj.ezcolet.models.order.GeneralModel
import proj.ezcolet.models.Model
import proj.ezcolet.models.order.ModelInt
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel

interface DatabaseService {
    suspend fun add(collectionName: String, document: Model)
    suspend fun update(collectionName: String, document: Model)
    suspend fun delete(collectionName: String, document: Model)
    suspend fun get(collectionName: String, documentId: String): Any?
}