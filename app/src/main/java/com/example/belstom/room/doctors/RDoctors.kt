package com.example.belstom.room.doctors

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors_table")
data class RDoctors(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val code : String,
    val surname: String,
    val name: String,
    val patronymic: String,
    val fio : String
)