package com.example.belstom.view.cabinet.news.recyclerNews

import java.io.Serializable

data class NewsItem(
    var data: String,
    var title: String,
    var content: String,
    var imagePath: String
) : Serializable
