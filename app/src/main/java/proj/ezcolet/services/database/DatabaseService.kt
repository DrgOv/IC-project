package proj.ezcolet.services.database

import proj.ezcolet.models.Model

interface DatabaseService {
    suspend fun add(collectionName: String, document: Model)
    suspend fun update(collectionName: String, document: Model)
    suspend fun delete(collectionName: String, document: Model)
    suspend fun get(collectionName: String, documentId: String): Any?
}