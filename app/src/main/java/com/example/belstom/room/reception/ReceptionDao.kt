package com.example.belstom.room.reception

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.belstom.room.news.RNews

@Dao
interface ReceptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReception(reception: RReception)

    @Query("SELECT * FROM reception_table ORDER BY id ASC")
    fun readAllReception(): List<RReception>

    @Query("SELECT * FROM reception_table ORDER BY id ASC")
    fun readAllReceptionLiveData(): LiveData<List<RReception>>

    @Query("DELETE FROM reception_table")
    fun deleteAllReceptions()




}