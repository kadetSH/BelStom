package com.example.belstom.view.cabinet.schedule.recyclerSchedule.businessHours

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class BusinessHoursAdapter(
    private val layoutInflater: LayoutInflater,
    private val itemsArray: ArrayList<BusinessHoursItem>,
    private val listener: ((businessHoursItem: BusinessHoursItem, position: Int) -> Unit)?
) : RecyclerView.Adapter<BusinessHoursHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessHoursHolder {
        return BusinessHoursHolder(
            layoutInflater.inflate(
                R.layout.template_business_hours,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusinessHoursHolder, position: Int) {
        holder.bind(itemsArray[position])

        holder.timeTitle.setOnClickListener {
            listener?.invoke(itemsArray[position], position)
        }
    }

    override fun getItemCount(): Int = itemsArray.size


}