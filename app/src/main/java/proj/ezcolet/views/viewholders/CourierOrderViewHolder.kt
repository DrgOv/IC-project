package proj.ezcolet.views.viewholders

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import proj.ezcolet.R
import proj.ezcolet.databinding.CourierCardItemBinding
import proj.ezcolet.models.order.OrderModel

class CourierOrderViewHolder(itemView: View) :
    OrderViewHolder(itemView) {
    private var binding: CourierCardItemBinding = CourierCardItemBinding.bind(itemView)
    override var upperTextView: TextView = binding.courierOrderNameTextView
    override var lowerTextView: TextView = binding.courierOrderDetailsTextView
    val checkImageBtn: ImageButton = binding.checkBtn
    val cancelImageBtn: ImageButton = binding.cancelBtn
    fun clickDialog(model: OrderModel) {
        itemView.setOnClickListener {
            openDialog(model)
        }
    }

    private fun openDialog(model: OrderModel) {
        val position: Int = absoluteAdapterPosition
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(itemView.context, R.style.MyAlertDialogTheme)
        builder.setTitle("Informații comanda ${position + 1}")
            .setMessage(
                "Comanda: " + model.orderName
                        + "\nNume: " + model.clientLastName + " " + model.clientFirstName
                        + "\nStrada: " + model.clientStreet
                        + "\nTelefon: " + model.clientPhone
                        + "\nSuma: " + model.orderSum
            )
            .setPositiveButton(
                "ok"
            ) { dialog, _ -> dialog.dismiss() }
            .setNeutralButton(
                "Copiază nr. telefon"
            ) { dialog, _ -> dialog.dismiss();copyNumber(model.clientPhone) }

        builder.create()
        builder.show()
    }

    private fun copyNumber(clientPhone: String) {
        val myClipboard =
            itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip: ClipData = ClipData.newPlainText("Label", clientPhone)
        myClipboard.setPrimaryClip(myClip)
        Toast.makeText(itemView.context, "Copiat", Toast.LENGTH_SHORT).show()
    }

}