package com.example.belstom.jsonMy

import java.io.Serializable

data class ClientItem(
    var surname: String,
    var name: String,
    var patronymic: String
) : Serializable
