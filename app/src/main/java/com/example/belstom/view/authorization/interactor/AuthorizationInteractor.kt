package com.example.belstom.view.authorization.interactor

import android.annotation.SuppressLint
import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceAuthorization
import com.example.belstom.jsonMy.Authorization
import com.example.belstom.jsonMy.IdRequest
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.contactInformation.ContactInformationDao
import com.example.belstom.room.doctors.DoctorsDao
import com.example.belstom.room.doctors.RDoctors
import com.example.belstom.room.reception.ReceptionDao
import com.example.belstom.room.schedule.DepartmentScheduleDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AuthorizationInteractor(
    private val retrofitServiceInterfaceAuthorization: RetrofitServiceInterfaceAuthorization,
    private val authorizationDao: AuthorizationDao,
    private val contactInformationDao: ContactInformationDao,
    private val departmentScheduleDao: DepartmentScheduleDao,
    private val doctorsDao: DoctorsDao,
    private val receptionsDao: ReceptionDao
) {

    fun getAuthorization(
        authorization: Authorization
    ): io.reactivex.Observable<IdRequest> {
        return retrofitServiceInterfaceAuthorization.authorization(authorization)
    }

    private fun getCountLogin(): Int {
        return authorizationDao.countLogin()
    }

    fun getLogin(): Observable<RLogin>? {
        val count: Int = getCountLogin()
        return if (count > 0) {
            authorizationDao.getLogin()
        } else null
    }

    private fun addLoginData(
        surname: String,
        name: String,
        patronymic: String,
        password: String,
        ui: String
    ) {
        val login = getRLogin(surname, name, patronymic, password, ui)
        authorizationDao.addLogin(login)
    }

    private fun getRLogin(
        surname: String,
        name: String,
        patronymic: String,
        password: String,
        ui: String
    ): RLogin {
        return RLogin(0, surname, name, patronymic, password, ui)
    }


    fun reconciliationOfUsers(
        surname: String,
        name: String,
        patronymic: String,
        password: String,
        ui: String
    ) {
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        clearDuplicateTables()
        if (firstLogin == null) {
            clearBase()
            addLoginData(surname, name, patronymic, password, ui)
        }

//        if (firstLogin.surname == surname && firstLogin.name == name && firstLogin.patronymic == patronymic && firstLogin.ui == ui) {
//            println()
//        } else {
//            clearBase()
//            addLoginData(surname, name, patronymic, password, ui)
//        }
    }

    private fun clearBase() {
        authorizationDao.deleteAllLogin()
        contactInformationDao.deleteAllContactInfo()
    }

    private fun clearDuplicateTables() {
        departmentScheduleDao.deleteAllDepartmentSchedule()
        doctorsDao.deleteAllDoctors()
        receptionsDao.deleteAllReceptions()
    }

    @SuppressLint("CheckResult")
    fun getDoctorsList() {
        val list = retrofitServiceInterfaceAuthorization.getDoctorsList()
        list
            .subscribeOn(Schedulers.io())
            .doOnError {
            }
            .subscribe({ result ->
                result.forEach { doc ->
                    doctorsDao.addDoctor(
                        RDoctors(
                            0,
                            doc.code,
                            doc.surname,
                            doc.name,
                            doc.patronymic,
                            doc.fio
                        )
                    )
                }
            }, { error ->
            })
    }


}