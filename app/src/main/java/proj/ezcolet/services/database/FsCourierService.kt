package proj.ezcolet.services.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import proj.ezcolet.models.users.CourierModel

object FsCourierService : FsDatabaseService() {
    private const val COURIERS_COLLECTION = "couriers"

    fun getCollectionRef(): CollectionReference {
        return super.db().collection(COURIERS_COLLECTION)
    }

    suspend fun addCourier(courier: CourierModel) {
        super.add(COURIERS_COLLECTION, courier)
    }

    suspend fun updateCourier(courier: CourierModel) {
        super.update(COURIERS_COLLECTION, courier)
    }

    suspend fun deleteCourier(courier: CourierModel) {
        super.delete(COURIERS_COLLECTION, courier)
    }

    suspend fun getCourier(id: String): CourierModel? {
        return super.get(COURIERS_COLLECTION, id)?.toObject()
    }
}