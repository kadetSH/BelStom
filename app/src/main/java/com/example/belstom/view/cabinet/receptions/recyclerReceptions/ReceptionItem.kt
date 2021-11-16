package com.example.belstom.view.cabinet.receptions.recyclerReceptions

import java.io.Serializable

data class ReceptionItem(
    var title: String,
    var date: String,
    var doctor: String,
    var cavity: String,
    var number: String
) : Serializable