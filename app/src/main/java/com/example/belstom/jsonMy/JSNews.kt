package com.example.belstom.jsonMy

import com.google.gson.annotations.SerializedName

data class JSNews(
    @SerializedName("Data") val data : String,
    @SerializedName("Title") val title : String,
    @SerializedName("Content") val content : String,
    @SerializedName("ImagePath") val imagePath : String,
    @SerializedName("NewsID") val newsId : String
)