package com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models

import com.example.belstom.jsonMy.ScheduleJS

object DayChildDataFactoryOfDoctors {

    fun getChildren(schedule: List<ScheduleJS>): List<DayChildModelOfDoctors>{
        val children = mutableListOf<DayChildModelOfDoctors>()
        schedule.forEach { item ->
            val child = DayChildModelOfDoctors(item.dayOfTheWeek, item.date, item.reception)
            children.add(child)
        }

        return children
    }

}