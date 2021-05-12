package proj.ezcolet.views.viewholders

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
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
    private val REQUEST_PHONE_CALL = 1
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
                "Sună"
            ) { dialog, _ -> dialog.dismiss();checkCallPermission(model.clientPhone) }

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

    private fun checkCallPermission(clientPhone: String) {
        if (ActivityCompat.checkSelfPermission(
                itemView.context,
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                itemView.context as Activity,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                REQUEST_PHONE_CALL
            )
        } else {
            callNumber(clientPhone)
        }
    }

    private fun callNumber(clientPhone: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.setData(Uri.parse("tel:" + clientPhone))
        itemView.context.startActivity(callIntent)

    }


}