package com.example.belstom.dagger.room

import android.app.Application
import com.example.belstom.room.LoginDatabase
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.contactInformation.ContactInformationDao
import com.example.belstom.room.doctors.DoctorsDao
import com.example.belstom.room.news.NewsDao
import com.example.belstom.room.schedule.DepartmentScheduleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun getRoomDbInstance(application: Application): LoginDatabase{
        return LoginDatabase.getLoginDatabase(application)
    }

    @Singleton
    @Provides
    fun getAuthorizationDaoDao(loginDatabase: LoginDatabase): AuthorizationDao {
        return loginDatabase.getAuthorizationDao()
    }

    @Singleton
    @Provides
    fun getContactInformationDao(loginDatabase: LoginDatabase): ContactInformationDao {
        return loginDatabase.getContactInformationDao()
    }

    @Singleton
    @Provides
    fun getNewsDao(loginDatabase: LoginDatabase): NewsDao {
        return loginDatabase.getNewsDao()
    }

    @Singleton
    @Provides
    fun getDepartmentScheduleDao(loginDatabase: LoginDatabase): DepartmentScheduleDao {
        return loginDatabase.getDepartmentScheduleDao()
    }

    @Singleton
    @Provides
    fun getDoctorsDao(loginDatabase: LoginDatabase): DoctorsDao {
        return loginDatabase.getDoctorsDao()
    }

}