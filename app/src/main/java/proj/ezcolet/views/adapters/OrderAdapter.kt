package proj.ezcolet.views.adapters

import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.OrderViewHolder

abstract class OrderAdapter(options: FirestoreRecyclerOptions<OrderModel>) :
    FirestoreRecyclerAdapter<OrderModel, OrderViewHolder>(options) {
}