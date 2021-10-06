package com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models

data class DayParentModelOfDoctors(
    val fio: String,
    val profession: String,
    val children: List<DayChildModelOfDoctors>
)