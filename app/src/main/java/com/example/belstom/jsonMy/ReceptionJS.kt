package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class ReceptionJS(
    @SerializedName("PatientUI") val patientUI : String,
    @SerializedName("Doctor") val doctor : String,
    @SerializedName("NumberReception") val numberReception : String
)
