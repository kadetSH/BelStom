package com.example.belstom.view.cabinet.schedule.recyclerSchedule.businessHours

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class BusinessHoursHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var timeTitle: TextView = itemView.findViewById<TextView>(R.id.id_bh_edit_time)

    fun bind(item: BusinessHoursItem) {
        timeTitle.text = item.time

        if (item.periodOfTime == 1) {
            var color : Int = Color.parseColor("#8ed16d")
            timeTitle.setBackgroundColor(color)
        }else{
            var colorGray : Int = Color.parseColor("#ABABAB")
            timeTitle.setBackgroundColor(colorGray)
        }
    }

}