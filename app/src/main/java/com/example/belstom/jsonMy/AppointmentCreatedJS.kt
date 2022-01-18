package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppointmentCreatedJS(
    @SerializedName("Data") val data : String,
    @SerializedName("Time") val time : String,
    @SerializedName("Profession") val profession : String,
    @SerializedName("Doctor") val doctor : String
) : Serializable
