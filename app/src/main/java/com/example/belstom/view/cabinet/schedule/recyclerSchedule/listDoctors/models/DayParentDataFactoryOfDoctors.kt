package com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models

import com.example.belstom.jsonMy.OfficeHoursJS
import com.example.belstom.jsonMy.ScheduleJS

object DayParentDataFactoryOfDoctors {

    private fun randomChildren(schedule: List<ScheduleJS>): List<DayChildModelOfDoctors>{
        return DayChildDataFactoryOfDoctors.getChildren(schedule)
    }

    fun getParents(listDoctors: ArrayList<OfficeHoursJS>): List<DayParentModelOfDoctors>{
        val parents = mutableListOf<DayParentModelOfDoctors>()
        listDoctors.forEach { item ->
            val parent = DayParentModelOfDoctors(item.doctor, item.profession, randomChildren(item.schedule))
            parents.add(parent)
        }
        return parents
    }
}