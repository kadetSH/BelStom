package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class ReceptionDescriptionAnswer(
    @SerializedName("Procedure") val procedure : String,
    @SerializedName("Cavity") val cavity : String
)
