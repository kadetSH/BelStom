package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class VisitsJS(
    @SerializedName("Title") val title : String,
    @SerializedName("Date") val date : String,
    @SerializedName("Doctor") val doctor : String
)
