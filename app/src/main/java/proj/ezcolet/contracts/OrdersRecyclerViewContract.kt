package proj.ezcolet.contracts

import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.views.viewholders.OrderViewHolder

interface OrdersRecyclerViewContract {
    interface ViewHolder {
        fun setUpperLowerTexts(upperText: String, lowerText: String)
    }

    interface ViewHolderPresenter {
        fun onSetUpperLowerTexts(upperText: String, lowerText: String)
    }

    interface Adapter {

    }

    interface AdapterPresenter {
        fun onBindVH(holder: OrderViewHolder, model: OrderModel)
        fun onBindVHCourier(holder: OrderViewHolder, model: OrderModel)
    }
}