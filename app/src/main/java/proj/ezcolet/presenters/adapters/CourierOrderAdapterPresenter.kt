package proj.ezcolet.presenters.adapters

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.CourierOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder

class CourierOrderAdapterPresenter : OrderAdapterPresenter() {
    override fun onBindVH(holder: OrderViewHolder, model: OrderModel) {
        super.onBindVH(holder, model)
        (holder as CourierOrderViewHolder).checkImageBtn.setOnClickListener {
            println("check+ $model")
        }
        (holder as CourierOrderViewHolder).cancelImageBtn.setOnClickListener {
            println("cancel + $model")
        }
    }
}