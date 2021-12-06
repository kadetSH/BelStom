package com.example.belstom.room.visits

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visits_table")
data class RVisits(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String,
    val doctor: String
)
