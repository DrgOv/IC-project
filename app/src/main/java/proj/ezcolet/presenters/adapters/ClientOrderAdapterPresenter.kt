package proj.ezcolet.presenters.adapters

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.ClientOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder

class ClientOrderAdapterPresenter : OrderAdapterPresenter() {
    override fun onBindVH(holder: OrderViewHolder, model: OrderModel) {
        super.onBindVH(holder, model)
        (holder as ClientOrderViewHolder).deliverImageBtn.setOnClickListener {
            println("hey + $model")
        }
    }
}