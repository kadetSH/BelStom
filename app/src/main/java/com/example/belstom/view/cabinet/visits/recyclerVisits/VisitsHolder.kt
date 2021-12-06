package com.example.belstom.view.cabinet.visits.recyclerVisits

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class VisitsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById<TextView>(R.id.template_visits_edit_title)
    private val date: TextView = itemView.findViewById<TextView>(R.id.template_visits_edit_date)
    private val doctor: TextView = itemView.findViewById<TextView>(R.id.template_visits_edit_doctor)

    fun bind(item: VisitsItem){
        title.text = item.title
        date.text = item.date
        doctor.text = item.doctor
    }

}