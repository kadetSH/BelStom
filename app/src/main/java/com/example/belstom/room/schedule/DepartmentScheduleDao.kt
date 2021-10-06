package com.example.belstom.room.schedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDepartmentSchedule(departmentSchedule: RDepartmentSchedule)

    @Query("SELECT * FROM department_schedule_table ORDER BY id ASC")
    fun readDepartmentSchedule(): List<RDepartmentSchedule>

    @Query("SELECT * FROM department_schedule_table WHERE  department= :department ORDER BY doctor ASC")
    fun readAllDepartmentSchedule(department: String): LiveData<List<RDepartmentSchedule>>

    @Query("DELETE FROM department_schedule_table")
    fun deleteAllDepartmentSchedule()

    @Query("SELECT  COUNT(id) FROM department_schedule_table WHERE  department= :department")
    fun countDepartmentSchedule(department: String): Int

}