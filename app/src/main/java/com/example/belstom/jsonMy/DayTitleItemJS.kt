package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class DayTitleItemJS(
    @SerializedName("FIO") val fio : String,
    @SerializedName("Profession") val profession : String
)
