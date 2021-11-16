package com.example.belstom.view.cabinet.receptions.recyclerReceptionDescription

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class ReceptionDescriptionHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val procedure: TextView = itemView.findViewById<TextView>(R.id.template_reception_description_edit_procedure)
    private val cavity: TextView = itemView.findViewById<TextView>(R.id.template_reception_description_edit_cavity)

    fun bind(item: ReceptionDescriptionItem){
        procedure.text = item.procedure
        cavity.text = item.cavity
    }
}