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
        var S:String=model.orderName
        val spannableString = SpannableString(S)
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                println("heloooooooooooooooooooooooooooo")
            }
        }

        spannableString.setSpan(clickableSpan1, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.upperTextView.movementMethod = LinkMovementMethod.getInstance()

        holder.setUpperLowerTexts(
            model.orderName,
            "Status: ${model.orderStatus.toLowerCase(Locale.ROOT)}"
        )

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
        model.orderStatus = "completed"
        FsOrderService.updateOrder(model)
    }

    private suspend fun cancelPressed(model: OrderModel) {
        model.orderStatus = "canceled"
        FsOrderService.updateOrder(model)
    }
}