package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class ReceptionListJS(
    @SerializedName("ReceptionNumber") val receptionNumber : String,
    @SerializedName("Title") val title : String,
    @SerializedName("DateOfReceipt") val dateOfReceipt : String,
    @SerializedName("Doctor") val doctor : String,
    @SerializedName("Cavity") val cavity : String
)