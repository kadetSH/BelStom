package com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayChildModelOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayParentModelOfDoctors

class DayParentAdapterOfDoctors(
    private val parents: List<DayParentModelOfDoctors>,
    private val listener: ((parentModel: DayParentModelOfDoctors, childModel: DayChildModelOfDoctors) -> Unit)
) :
    RecyclerView.Adapter<DayParentAdapterOfDoctors.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editFIO: TextView = itemView.findViewById(R.id.id_parent_period_days_edit_fio)
        val editProfession: TextView =
            itemView.findViewById(R.id.id_parent_period_days_edit_profession)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.id_parent_period_days_rv_child)
    }

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_parent_period_days, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = parents[position]
        holder.editFIO.text = parent.fio
        holder.editProfession.text = parent.profession
        holder.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(holder.recyclerView.context, LinearLayout.HORIZONTAL, false)
            adapter =
                DayChildAdapterOfDoctors(parent.children) { childModel: DayChildModelOfDoctors ->
                    listener?.invoke(parent, childModel)
                }
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int = parents.size



}