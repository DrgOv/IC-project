package proj.ezcolet.services.database

import proj.ezcolet.models.Model

interface DatabaseService {
    fun add(collectionName: String, document: Model)
    fun update(collectionName: String, document: Model)
    fun delete(collectionName: String, document: Model)
    fun get(collectionName: String, documentId: String): Model?
}