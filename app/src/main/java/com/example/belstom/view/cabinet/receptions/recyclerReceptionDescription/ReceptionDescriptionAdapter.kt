package com.example.belstom.view.cabinet.receptions.recyclerReceptionDescription

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class ReceptionDescriptionAdapter(
    private val layoutInflater: LayoutInflater,
    private val itemsArray: ArrayList<ReceptionDescriptionItem>,
    private val listener: ((descriptionItem: ReceptionDescriptionItem, position: Int) -> Unit)?
) : RecyclerView.Adapter<ReceptionDescriptionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceptionDescriptionHolder {
        return ReceptionDescriptionHolder(layoutInflater.inflate(R.layout.template_reception_description, parent, false))
    }

    override fun onBindViewHolder(holder: ReceptionDescriptionHolder, position: Int) {
        holder.bind(itemsArray[position])
    }

    override fun getItemCount(): Int = itemsArray.size
}