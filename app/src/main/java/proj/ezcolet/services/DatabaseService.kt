package proj.ezcolet.services

import proj.ezcolet.models.ClientModel
import proj.ezcolet.models.Model

interface DatabaseService {
    fun add(collectionName: String, document: Model)
    fun update(collectionName: String, document: Model)
    fun delete(collectionName: String, document: Model)
}