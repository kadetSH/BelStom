package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class DayItemJS(
    @SerializedName("Day") val day : String,
    @SerializedName("Date") val date : String,
    @SerializedName("Reception") val reception : String
)
