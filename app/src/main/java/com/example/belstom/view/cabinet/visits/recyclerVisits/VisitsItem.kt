package com.example.belstom.view.cabinet.visits.recyclerVisits

import java.io.Serializable

data class VisitsItem(
    var title: String,
    var date: String,
    var doctor: String
) : Serializable