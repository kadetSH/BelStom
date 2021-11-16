package com.example.belstom.room.reception

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reception_table")
data class RReception(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val receptionNumber : String,
    val title: String,
    val dateOfReceipt: String,
    val doctor: String,
    val cavity : String
)
