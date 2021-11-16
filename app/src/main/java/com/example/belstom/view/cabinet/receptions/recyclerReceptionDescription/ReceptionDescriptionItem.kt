package com.example.belstom.view.cabinet.receptions.recyclerReceptionDescription

import java.io.Serializable

data class ReceptionDescriptionItem(
    var procedure: String,
    var cavity: String
) : Serializable