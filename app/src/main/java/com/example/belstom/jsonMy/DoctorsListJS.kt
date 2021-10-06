package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DoctorsListJS(
    @SerializedName("Code") val code : String,
    @SerializedName("Surname") val surname : String,
    @SerializedName("Name") val name : String,
    @SerializedName("Patronymic") val patronymic : String,
    @SerializedName("FIO") val fio : String
): Serializable
