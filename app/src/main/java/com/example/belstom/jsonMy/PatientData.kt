package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class PatientData(
    @SerializedName("Surname") val surname : String,
    @SerializedName("Name") val name : String,
    @SerializedName("Patronymic") val patronymic : String,
    @SerializedName("Birth") val birth : String,
    @SerializedName("Age") val age : String,
    @SerializedName("Gender") val gender : String,
    @SerializedName("Telephone") val telephone : String,
    @SerializedName("Address") val address : String,
    @SerializedName("ID1c") val id1c : String
)
