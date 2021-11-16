package com.example.belstom.view.cabinet.receptions.recyclerReceptions

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R


class ReceptionHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById<TextView>(R.id.template_reception_edit_title)
    private val date: TextView = itemView.findViewById<TextView>(R.id.template_reception_edit_date)
    private val doctor: TextView = itemView.findViewById<TextView>(R.id.template_reception_edit_doctor)
    private val cavity: TextView = itemView.findViewById<TextView>(R.id.template_reception_edit_cavity)
    private val number: TextView = itemView.findViewById<TextView>(R.id.template_reception_edit_number)
//    val iconOpen: ImageView = itemView.findViewById<ImageView>(R.id.template_reception_icon_open_description)


    fun bind(item: ReceptionItem){
        title.text = item.title
        date.text = item.date
        doctor.text = item.doctor
        cavity.text = item.cavity
        number.text = item.number
    }

}