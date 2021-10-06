package com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R
import com.example.belstom.view.cabinet.news.recyclerNews.NewsItem
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayChildModelOfDoctors

class DayChildAdapterOfDoctors(
    private val children: List<DayChildModelOfDoctors>,
    private val listener: ((item: DayChildModelOfDoctors) -> Unit)) :
    RecyclerView.Adapter<DayChildAdapterOfDoctors.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDay: TextView = itemView.findViewById(R.id.id_oh_edit_day_RC00)
        val textDate: TextView = itemView.findViewById(R.id.id_oh_edit_date_RC00)
        val textReception: TextView = itemView.findViewById(R.id.id_oh_edit_reception_RC00)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_child_period_days, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val child = children[position]

        holder.textDay.text = child.day
        holder.textDate.text = child.date
        holder.textReception.text = child.reception
        holder.textDate.setOnClickListener {
            listener?.invoke(child)
        }
    }

    override fun getItemCount(): Int = children.size
}