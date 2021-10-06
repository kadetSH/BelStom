package com.example.belstom.room.contactInformation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.belstom.room.authorization.RLogin

@Dao
interface ContactInformationDao {
    @Query("SELECT * FROM contact_info_table ORDER BY id ASC")
    fun readAllContractInfo(): List<RContactInformation>

    @Query("SELECT * FROM contact_info_table ORDER BY id ASC")
    fun readAllContractInfoLiveData(): LiveData<List<RContactInformation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContactInfo(info: RContactInformation)

    @Query("DELETE FROM contact_info_table")
    fun deleteAllContactInfo()

    @Query("SELECT * FROM contact_info_table WHERE ui = :ui")
    fun getContact(ui: String): RContactInformation

    @Query("UPDATE contact_info_table SET birth = :birth WHERE ui = :ui")
    fun updateBirth(ui: String, birth: String)

    @Query("UPDATE contact_info_table SET age = :age WHERE ui = :ui")
    fun updateAge(ui: String, age: String)

    @Query("UPDATE contact_info_table SET address = :address WHERE ui = :ui")
    fun updateAddress(ui: String, address: String)
}