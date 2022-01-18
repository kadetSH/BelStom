package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class AnswerOfCreateAnAppointmentJS(
    @SerializedName("Answer") val answer : String,
    @SerializedName("Data") val data : String,
    @SerializedName("Time") val time : String,
    @SerializedName("Profession") val profession : String,
    @SerializedName("Doctor") val doctor : String
)

