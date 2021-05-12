package proj.ezcolet.presenters.adapters

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.CourierCardItemBinding
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.models.order.CANCELED
import proj.ezcolet.models.order.COMPLETED
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.views.courier.CourierHomeActivity
import proj.ezcolet.views.viewholders.CourierOrderViewHolder
import proj.ezcolet.views.viewholders.OrderViewHolder
import java.util.*
import kotlin.coroutines.CoroutineContext

class CourierOrderAdapterPresenter(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    OrderAdapterPresenter<CourierOrderViewHolder>(),
    CoroutineScope {
    override fun onBindVH(holder: CourierOrderViewHolder, model: OrderModel) {
        holder.setUpperLowerTexts(
            " ${holder.absoluteAdapterPosition + 1}. "+model.orderName,
            "Status: ${model.orderStatus.toLowerCase(Locale.ROOT)}"
        )
        holder.clickDialog(model)
        holder.checkImageBtn.setOnClickListener {
            launch {
                println("check+ $model")
                checkPressed(model)
            }

        }
        holder.cancelImageBtn.setOnClickListener {
            launch {
                println("cancel + $model")
                cancelPressed(model)
            }
        }
    }

    private suspend fun checkPressed(model: OrderModel) {
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
    }

    private suspend fun cancelPressed(model: OrderModel) {
        model.orderStatus = CANCELED
        FsOrderService.updateOrder(model)
    }
}