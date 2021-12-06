package com.example.belstom.view.cabinet.visits.recyclerVisits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class VisitsAdapter (private val layoutInflater: LayoutInflater,
                    private val itemsArray: ArrayList<VisitsItem>,
                    private val listener: ((visitsItem: VisitsItem, position: Int) -> Unit)?):  RecyclerView.Adapter<VisitsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitsHolder {
        return VisitsHolder(layoutInflater.inflate(R.layout.template_visits, parent, false))
    }

    override fun onBindViewHolder(holder: VisitsHolder, position: Int) {
        holder.bind(itemsArray[position])
    }

    override fun getItemCount(): Int = itemsArray.size
}