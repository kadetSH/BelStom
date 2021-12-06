package com.example.belstom.room.visits

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VisitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVisits(visits: RVisits)

    @Query("SELECT * FROM visits_table ORDER BY id ASC")
    fun readAllVisits(): List<RVisits>

    @Query("SELECT * FROM visits_table ORDER BY id ASC")
    fun readAllVisitsLiveData(): LiveData<List<RVisits>>

    @Query("DELETE FROM visits_table")
    fun deleteAllVisits()

}