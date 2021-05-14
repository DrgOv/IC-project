package proj.ezcolet.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import proj.ezcolet.R
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.presenters.adapters.CourierOrderAdapterPresenter
import proj.ezcolet.views.viewholders.CourierOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder

class CourierOrderAdapter(
    options: FirestoreRecyclerOptions<OrderModel>,
    val username: String
) :

    OrderAdapter(options) {
    private var courierOrderAdapterPresenter = CourierOrderAdapterPresenter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return CourierOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.courier_card_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int, model: OrderModel) {
        courierOrderAdapterPresenter.onBindVH(holder as CourierOrderViewHolder, model,username)
    }

}