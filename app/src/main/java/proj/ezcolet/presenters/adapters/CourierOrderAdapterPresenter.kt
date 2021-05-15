package proj.ezcolet.presenters.adapters


import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.models.order.CANCELED
import proj.ezcolet.models.order.COMPLETED
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.database.FsCourierService
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.views.viewholders.CourierOrderViewHolder
import java.util.*
import kotlin.coroutines.CoroutineContext

class CourierOrderAdapterPresenter(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    OrderAdapterPresenter<CourierOrderViewHolder>(),
    CoroutineScope {

    override fun onBindVH(
        holder: CourierOrderViewHolder,
        model: OrderModel,
        username: String
    ) {
        holder.setUpperLowerTexts(
            " ${holder.absoluteAdapterPosition + 1}. " + model.orderName,
            "Status: ${model.orderStatus.toLowerCase(Locale.ROOT)}"
        )
        holder.clickDialog(model)
        holder.checkImageBtn.setOnClickListener {
            launch {
                println("check+ $model")
                checkPressed(model,username,holder)
            }

        }
        holder.cancelImageBtn.setOnClickListener {
            launch {
                println("cancel + $model")
                cancelPressed(model,holder)
            }
        }
    }

    private suspend fun checkPressed(
        model: OrderModel,
        username: String,
        holder: CourierOrderViewHolder
    ) {
        if(model.orderStatus!="în curs de livrare")
        {
            Toast.makeText(holder.itemView.context, "Comanda este într-o stare finală", Toast.LENGTH_SHORT).show()
        }
        else {
            val calendar: Calendar = Calendar.getInstance()
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val hourStr = hour.toString()

            val minute: Int = calendar.get(Calendar.MINUTE)
            val minuteStr = minute.toString()

            val second: Int = calendar.get(Calendar.SECOND)
            val secondStr = second.toString()

            model.orderDetails = hourStr + ":" + minuteStr + ":" + secondStr;
            model.orderStatus = COMPLETED
            FsOrderService.updateOrder(model)

            var courier = FsCourierService.getCourier(username)
            println(courier)

            if (courier != null) {
                courier.totalOrders++
                println(courier.monthlyOrders)
                courier.monthlyOrders++
                println(courier.monthlyOrders)


            }
            if (courier != null) {
                FsCourierService.updateCourier(courier)
            }

        }

    }

    private suspend fun cancelPressed(
        model: OrderModel,
        holder: CourierOrderViewHolder
    ) {
        if(model.orderStatus!="în curs de livrare")
        {
            Toast.makeText(holder.itemView.context, "Comanda este într-o stare finală", Toast.LENGTH_SHORT).show()
        }
        else {
            model.orderStatus = CANCELED
            FsOrderService.updateOrder(model)
        }
    }
}