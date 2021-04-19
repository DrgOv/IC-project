package proj.ezcolet.presenters.client

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.services.database.FsClientService
import proj.ezcolet.views.client.ClientHomeActivity

class ClientHomePresenter(private val clientHomeActivity: ClientHomeActivity) {
    private var client: ClientModel? = null

    init {
        GlobalScope.launch {
            client = clientHomeActivity.intent.getStringExtra("Username")
                ?.let { FsClientService.getClient(it) }!!
        }
    }
}