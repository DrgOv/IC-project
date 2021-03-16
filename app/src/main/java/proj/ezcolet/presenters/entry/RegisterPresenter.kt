package proj.ezcolet.presenters.entry

import proj.ezcolet.models.ClientModel
import proj.ezcolet.services.FsDatabaseService
import proj.ezcolet.views.entry.RegisterActivity

class RegisterPresenter(registerActivity: RegisterActivity) {
    private val fsDatabaseService = FsDatabaseService()

    fun addClient(newClient: ClientModel) {
        fsDatabaseService.add(FsDatabaseService.CLIENTS_COLLECTION, newClient)
    }
}