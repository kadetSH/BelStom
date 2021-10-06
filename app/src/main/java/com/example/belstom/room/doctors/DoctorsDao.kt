package com.example.belstom.room.doctors

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.belstom.room.news.RNews
import io.reactivex.Observable

@Dao
interface DoctorsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDoctor(news: RDoctors)

    @Query("SELECT * FROM doctors_table ORDER BY id ASC")
    fun readAllDoctors(): List<RDoctors>

    @Query("SELECT * FROM doctors_table ORDER BY id ASC")
    fun readAllDoctorsLiveData(): LiveData<List<RDoctors>>

    @Query("DELETE FROM doctors_table")
    fun deleteAllDoctors()

    @Query("SELECT * FROM doctors_table WHERE fio = :fio LIMIT 1")
    fun getDoctor(fio: String): Observable<RDoctors>
}