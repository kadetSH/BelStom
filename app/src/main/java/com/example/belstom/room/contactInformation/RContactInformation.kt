package com.example.belstom.room.contactInformation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_info_table")
data class RContactInformation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val ui: String,
    val surname: String,
    val name: String,
    val patronymic: String,
    val birth: String,
    val age: String,
    val gender: String,
    val telephone: String,
    val address: String,
    val id1c: String
)
