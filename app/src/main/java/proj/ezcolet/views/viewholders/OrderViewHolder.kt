package proj.ezcolet.views.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

abstract class OrderViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract var upperTextView: TextView
    abstract var lowerTextView: TextView

    fun setUpperLowerTexts(upperText: String, lowerText: String) {
        upperTextView.text = upperText
        lowerTextView.text = lowerText
    }
}