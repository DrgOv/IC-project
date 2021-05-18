package proj.ezcolet.presenters.adapters

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import proj.ezcolet.models.order.NEEDED
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.services.validation.NUMBER
import proj.ezcolet.views.viewholders.ClientOrderViewHolder
import java.util.*

class ClientOrderAdapterPresenter(val client: ClientModel) :
    OrderAdapterPresenter<ClientOrderViewHolder>() {
    override fun onBindVH(
        holder: ClientOrderViewHolder,
        model: OrderModel,
        username: String
    ) {
        holder.setUpperLowerTexts(
            model.orderName,
            "Livrat la ora: ${model.orderDetails.toUpperCase(Locale.ROOT)}"
        )

        if (model.isClientOrder(client)) {
            holder.deliverImageBtn.setOnClickListener() {
                if (model.orderStatus != NEEDED) {
                    model.orderStatus = NEEDED
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