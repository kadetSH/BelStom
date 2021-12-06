package com.example.belstom.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.contactInformation.ContactInformationDao
import com.example.belstom.room.contactInformation.RContactInformation
import com.example.belstom.room.doctors.DoctorsDao
import com.example.belstom.room.doctors.RDoctors
import com.example.belstom.room.news.NewsDao
import com.example.belstom.room.news.RNews
import com.example.belstom.room.reception.RReception
import com.example.belstom.room.reception.ReceptionDao
import com.example.belstom.room.schedule.DepartmentScheduleDao
import com.example.belstom.room.schedule.RDepartmentSchedule
import com.example.belstom.room.visits.RVisits
import com.example.belstom.room.visits.VisitsDao

@Database(
    entities = [RLogin::class, RContactInformation::class, RNews::class, RDepartmentSchedule::class,
        RDoctors::class, RReception::class, RVisits::class], version = 9, exportSchema = false
)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun getAuthorizationDao(): AuthorizationDao
    abstract fun getContactInformationDao(): ContactInformationDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getDepartmentScheduleDao(): DepartmentScheduleDao
    abstract fun getDoctorsDao(): DoctorsDao
    abstract fun getReceptionDao(): ReceptionDao
    abstract fun getVisitsDao(): VisitsDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        @Suppress("LocalVariableName")
        fun getLoginDatabase(application: Application): LoginDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

//            val MIGRATION_1_2 = object : Migration(1, 2) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL(
//                        "CREATE TABLE `login_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `surname` TEXT NOT NULL, `name` TEXT NOT NULL, `patronymic` TEXT NOT NULL, `birth` TEXT NOT NULL, `gender` TEXT NOT NULL, `telephone` TEXT NOT NULL, `address` TEXT NOT NULL, `ui` TEXT NOT NULL)"
//                    )
//                }
//            }
//
//            val MIGRATION_2_3 = object : Migration(2, 3) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL(
//                        "CREATE TABLE `contact_info_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ui` TEXT NOT NULL, `surname` TEXT NOT NULL, `name` TEXT NOT NULL, `patronymic` TEXT NOT NULL, `birth` TEXT NOT NULL, `age` TEXT NOT NULL, `gender` TEXT NOT NULL, `telephone` TEXT NOT NULL, `address` TEXT NOT NULL, `id1c` TEXT NOT NULL)"
//                    )
//                }
//            }

//            val MIGRATION_3_4 = object : Migration(3, 4) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL(
//                        "CREATE TABLE `news_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `data` TEXT NOT NULL, `title` TEXT NOT NULL, `content` TEXT NOT NULL, `imagePath` TEXT NOT NULL, `newsId` TEXT NOT NULL)"
//                    )
//                }
//            }

//            val MIGRATION_3_4 = object : Migration(3, 4) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL(
//                        "CREATE TABLE `department_schedule_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `department` TEXT NOT NULL, `doctor` TEXT NOT NULL, `dayStr` TEXT NOT NULL, `dayDate` TEXT NOT NULL, `reception` TEXT NOT NULL)"
//                    )
//                }
//            }

            val MIGRATION_6_7 = object : Migration(6, 7) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "CREATE TABLE `doctors_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `code` TEXT NOT NULL, `surname` TEXT NOT NULL, `name` TEXT NOT NULL, `patronymic` TEXT NOT NULL, `fio` TEXT NOT NULL)"
                    )
                }
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    LoginDatabase::class.java,
                    "mobil3"
                )
                    .fallbackToDestructiveMigration()
//                    .addMigrations(
//                        MIGRATION_3_4
//                        MIGRATION_2_3
////                        MIGRATION_3_4
//                        MIGRATION_6_7
//                    )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }

}