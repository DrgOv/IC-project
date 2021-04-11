package proj.ezcolet.services.database

import proj.ezcolet.models.GeneralModel
import proj.ezcolet.models.Model
import proj.ezcolet.models.ModelInt
import proj.ezcolet.models.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.CourierModel

interface DatabaseService {
    suspend fun add(collectionName: String, document: Model)
    suspend fun addInt(collectionName: String, document: ModelInt)
    suspend fun addClient(client: ClientModel)
    suspend fun addCourier(courier: CourierModel)
    suspend fun addOrder(order: OrderModel)
    suspend fun addGeneral(general: GeneralModel)
    suspend fun update(collectionName: String, document: Model)
    suspend fun updateInt(collectionName: String, document: ModelInt)
    suspend fun updateClient(client: ClientModel)
    suspend fun updateCourier(courier: CourierModel)
    suspend fun updateOrder(order: OrderModel)
    suspend fun delete(collectionName: String, document: Model)
    suspend fun deleteInt(collectionName: String, document: ModelInt)
    suspend fun deleteClient(client: ClientModel)
    suspend fun deleteCourier(courier: CourierModel)
    suspend fun deleteOrder(order: OrderModel)
    suspend fun get(collectionName: String, documentId: String): Any?
    suspend fun getClient(documentId: String): ClientModel?
    suspend fun getCourier(documentId: String): CourierModel?
    suspend fun getOrder(documentId: String) : OrderModel?
    suspend fun getGeneral(documentId: String) : GeneralModel?
}