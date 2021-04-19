package proj.ezcolet.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import proj.ezcolet.R
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.presenters.adapters.ClientOrderAdapterPresenter
import proj.ezcolet.presenters.adapters.OrderAdapterPresenter
import proj.ezcolet.views.viewholders.ClientOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder

class ClientOrderAdapter(val client: ClientModel, options: FirestoreRecyclerOptions<OrderModel>) :
    OrderAdapter(options) {
    private var clientOrderAdapterPresenter = ClientOrderAdapterPresenter(client)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return ClientOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.client_card_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int, model: OrderModel) {
        clientOrderAdapterPresenter.onBindVH(holder as ClientOrderViewHolder, model)
    }
}