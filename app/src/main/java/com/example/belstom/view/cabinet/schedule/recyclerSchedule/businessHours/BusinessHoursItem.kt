package com.example.belstom.view.cabinet.schedule.recyclerSchedule.businessHours

import java.io.Serializable

data class BusinessHoursItem(
    var time: String,
    var periodOfTime: Int
) : Serializable