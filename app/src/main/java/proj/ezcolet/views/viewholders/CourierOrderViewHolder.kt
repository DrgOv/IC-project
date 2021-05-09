package proj.ezcolet.views.viewholders

import android.app.AlertDialog
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import proj.ezcolet.R
import proj.ezcolet.databinding.CourierCardItemBinding

class CourierOrderViewHolder(itemView: View) :
    OrderViewHolder(itemView) {
    private var binding: CourierCardItemBinding = CourierCardItemBinding.bind(itemView)
    override var upperTextView: TextView = binding.courierOrderNameTextView
    override var lowerTextView: TextView = binding.courierOrderDetailsTextView
    val checkImageBtn: ImageButton = binding.checkBtn
    val cancelImageBtn: ImageButton = binding.cancelBtn
    init {
        itemView.setOnClickListener {
            openDialog()
        }
    }
    fun openDialog() {
        val position: Int = absoluteAdapterPosition
        val builder: AlertDialog.Builder = AlertDialog.Builder(itemView.context, R.style.MyAlertDialogTheme)
        builder.setTitle("Informații")
            .setMessage("Ai apăsat pe obiectul ${position + 1}")
            .setPositiveButton(
                "ok"
            ) { dialog, _ -> dialog.dismiss() }
        builder.create()
        builder.show()
    }

}