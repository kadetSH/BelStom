package com.example.belstom.view.cabinet.receptions.recyclerReceptions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class ReceptionAdapter(private val layoutInflater: LayoutInflater,
                       private val itemsArray: ArrayList<ReceptionItem>,
                       private val listener: ((newsItem: ReceptionItem, position: Int) -> Unit)?):  RecyclerView.Adapter<ReceptionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceptionHolder {
        return ReceptionHolder(layoutInflater.inflate(R.layout.template_reception, parent, false))
    }

    override fun onBindViewHolder(holder: ReceptionHolder, position: Int) {
        holder.bind(itemsArray[position])

        holder.title.setOnClickListener {
            listener?.invoke(itemsArray[position], position)
        }
    }

    override fun getItemCount(): Int = itemsArray.size
}