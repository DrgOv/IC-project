package proj.ezcolet.presenters.adapters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.views.viewholders.CourierOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*
import kotlin.coroutines.CoroutineContext
class CourierOrderAdapterPresenter(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    OrderAdapterPresenter(),
    CoroutineScope {
    override fun onBindVHCourier(holder: OrderViewHolder, model: OrderModel) {
        super.onBindVHCourier(holder, model)
        (holder as CourierOrderViewHolder).checkImageBtn.setOnClickListener {
            launch {
                println("check+ $model")
                checkPressed(model)
            }

        }
        (holder as CourierOrderViewHolder).cancelImageBtn.setOnClickListener {
            launch {
                println("cancel + $model")
                cancelPressed(model)
            }
        }
    }

    suspend fun checkPressed(model: OrderModel) {
        val calendar: Calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val hourStr = hour.toString()

        val minute: Int = calendar.get(Calendar.MINUTE)
        val minuteStr = minute.toString()

        val second: Int = calendar.get(Calendar.SECOND)
        val secondStr = second.toString()

        model.orderDetails = hourStr + ":" + minuteStr + ":" + secondStr;
        model.orderStatus = "completed"
        FsOrderService.updateOrder(model)
    }

    suspend fun cancelPressed(model: OrderModel) {
        model.orderStatus = "canceled"
        FsOrderService.updateOrder(model)
    }
}