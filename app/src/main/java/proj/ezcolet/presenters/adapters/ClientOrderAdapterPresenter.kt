package proj.ezcolet.presenters.adapters

import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.views.viewholders.ClientOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*

class ClientOrderAdapterPresenter(val client: ClientModel) :
    OrderAdapterPresenter<ClientOrderViewHolder>() {
    override fun onBindVH(holder: ClientOrderViewHolder, model: OrderModel) {
        holder.setUpperLowerTexts(
            model.orderName,
            "Livrat la ora: ${model.orderDetails.toUpperCase(Locale.ROOT)}"
        )

        if (model.isClientOrder(client)) {
            holder.deliverImageBtn.setOnClickListener() {
                if (model.orderStatus != "needed") {
                    model.orderStatus = "needed"
                    GlobalScope.launch { FsOrderService.updateOrder(model) }
                } else {
                    holder.hideDeliverBtn()
                }
            }
        } else {
            holder.setUpperText("Comanda ${holder.layoutPosition + 1}")
            holder.hideDeliverBtn()
        }
    }
}