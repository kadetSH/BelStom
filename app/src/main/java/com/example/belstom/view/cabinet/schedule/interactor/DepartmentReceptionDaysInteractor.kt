package com.example.belstom.view.cabinet.schedule.interactor

import androidx.lifecycle.LiveData
import com.example.belstom.Constants
import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceSchedule
import com.example.belstom.jsonMy.*
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.doctors.DoctorsDao
import com.example.belstom.room.doctors.RDoctors
import com.example.belstom.room.schedule.DepartmentScheduleDao
import com.example.belstom.room.schedule.RDepartmentSchedule
import com.example.belstom.room.visits.VisitsDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DepartmentReceptionDaysInteractor(
    private val retrofitServiceInterfaceSchedule: RetrofitServiceInterfaceSchedule,
    private val departmentScheduleDao: DepartmentScheduleDao,
    private val authorizationDao: AuthorizationDao,
    private val doctorsDao: DoctorsDao,
    private val visitsDao: VisitsDao
) {

    fun getLiveDate(department: String): LiveData<List<RDepartmentSchedule>> {
        return departmentScheduleDao.readAllDepartmentSchedule(department)
    }

    fun getDoctorsLiveData(): LiveData<List<RDoctors>>{
        return doctorsDao.readAllDoctorsLiveData()
    }

    fun getListDoctors(department: String) {
        val departmentJS = getDepartmentJS2(department)
        val countDepartment: Int = departmentScheduleDao.countDepartmentSchedule(department)
        if (countDepartment == 0) {
            getScheduleDepartment(departmentJS)
        }
    }

    private fun getDepartmentJS2(department: String): DepartmentJS2 {
        val daysCount = Constants.DAYS_COUNT
        return DepartmentJS2(department, daysCount)
    }

    fun getPatientUI(): String{
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        return firstLogin.ui
    }

    fun clearVisitDao(){
        visitsDao.deleteAllVisits()
    }

    fun makeToAppointment(anAppointmentJS: CreateAnAppointmentJS): Observable<AnswerOfCreateAnAppointmentJS> {
        return retrofitServiceInterfaceSchedule.createAnAppointmentRequest(anAppointmentJS)
    }

    private fun getScheduleDepartment(departmentJS: DepartmentJS2) {
        val schedule = retrofitServiceInterfaceSchedule.patientOfficeHoursRequest(departmentJS)
            .subscribeOn(Schedulers.io())
            .doOnError {
                println("")
//                _toastMessage.postValue(it.toString())
            }
            .subscribe(
                { result ->
                    println("")
                    result.forEach { doctor ->
                        val listSchedule = ArrayList<ScheduleJS>()
                        doctor.schedule.forEach { schedule ->
                            listSchedule.add(
                                ScheduleJS(
                                    schedule.date,
                                    schedule.dayOfTheWeek,
                                    schedule.reception
                                )
                            )
                        }
                        println("")
                        departmentScheduleDao.addDepartmentSchedule(
                            RDepartmentSchedule(
                                0,
                                departmentJS.department,
                                doctor.doctor,
                                doctor.profession,
                                listSchedule[0].dayOfTheWeek,
                                listSchedule[0].date,
                                listSchedule[0].reception,
                                listSchedule[1].dayOfTheWeek,
                                listSchedule[1].date,
                                listSchedule[1].reception,
                                listSchedule[2].dayOfTheWeek,
                                listSchedule[2].date,
                                listSchedule[2].reception,
                                listSchedule[3].dayOfTheWeek,
                                listSchedule[3].date,
                                listSchedule[3].reception,
                                listSchedule[4].dayOfTheWeek,
                                listSchedule[4].date,
                                listSchedule[4].reception,
                                listSchedule[5].dayOfTheWeek,
                                listSchedule[5].date,
                                listSchedule[5].reception,
                                listSchedule[6].dayOfTheWeek,
                                listSchedule[6].date,
                                listSchedule[6].reception,
                                listSchedule[7].dayOfTheWeek,
                                listSchedule[7].date,
                                listSchedule[7].reception,
                                listSchedule[8].dayOfTheWeek,
                                listSchedule[8].date,
                                listSchedule[8].reception,
                                listSchedule[9].dayOfTheWeek,
                                listSchedule[9].date,
                                listSchedule[9].reception,
                                listSchedule[10].dayOfTheWeek,
                                listSchedule[10].date,
                                listSchedule[10].reception,
                                listSchedule[11].dayOfTheWeek,
                                listSchedule[11].date,
                                listSchedule[11].reception,
                                listSchedule[12].dayOfTheWeek,
                                listSchedule[12].date,
                                listSchedule[12].reception,
                                listSchedule[13].dayOfTheWeek,
                                listSchedule[13].date,
                                listSchedule[13].reception,
                                listSchedule[14].dayOfTheWeek,
                                listSchedule[14].date,
                                listSchedule[14].reception,
                                listSchedule[15].dayOfTheWeek,
                                listSchedule[15].date,
                                listSchedule[15].reception,
                                listSchedule[16].dayOfTheWeek,
                                listSchedule[16].date,
                                listSchedule[16].reception,
                                listSchedule[17].dayOfTheWeek,
                                listSchedule[17].date,
                                listSchedule[17].reception,
                                listSchedule[18].dayOfTheWeek,
                                listSchedule[18].date,
                                listSchedule[18].reception,
                                listSchedule[19].dayOfTheWeek,
                                listSchedule[19].date,
                                listSchedule[19].reception,
                                listSchedule[20].dayOfTheWeek,
                                listSchedule[20].date,
                                listSchedule[20].reception,
                                listSchedule[21].dayOfTheWeek,
                                listSchedule[21].date,
                                listSchedule[21].reception,
                                listSchedule[22].dayOfTheWeek,
                                listSchedule[22].date,
                                listSchedule[22].reception,
                                listSchedule[23].dayOfTheWeek,
                                listSchedule[23].date,
                                listSchedule[23].reception,
                                listSchedule[24].dayOfTheWeek,
                                listSchedule[24].date,
                                listSchedule[24].reception,
                                listSchedule[25].dayOfTheWeek,
                                listSchedule[25].date,
                                listSchedule[25].reception,
                                listSchedule[26].dayOfTheWeek,
                                listSchedule[26].date,
                                listSchedule[26].reception,
                                listSchedule[27].dayOfTheWeek,
                                listSchedule[27].date,
                                listSchedule[27].reception,
                                listSchedule[28].dayOfTheWeek,
                                listSchedule[28].date,
                                listSchedule[28].reception,
                                listSchedule[29].dayOfTheWeek,
                                listSchedule[29].date,
                                listSchedule[29].reception
                            )
                        )
                    }
//                    _listOfficeHours.postValue(result)
                },
                { error ->
                    println("")
//                    _toastMessage.postValue(error.toString())
                }
            )
    }

    fun getBusinessHours(
        doctorRequest: String,
        dateRequest: String,
        periodOfTimeRequest: String
    ): Observable<ArrayList<BusinessHoursResultJS>> {
        val json = getRequestDoctorRequestsJS(doctorRequest, dateRequest, periodOfTimeRequest)
        return retrofitServiceInterfaceSchedule.businessHoursRequest(json)
    }

    private fun getRequestDoctorRequestsJS(
        doctorRequest: String,
        dateRequest: String,
        periodOfTimeRequest: String
    ): RequestDoctorRequestsJS {
        return RequestDoctorRequestsJS(doctorRequest, dateRequest, periodOfTimeRequest)
    }

    fun checkFIO(docFIO: String): Observable<RDoctors>{
        return doctorsDao.getDoctor(docFIO)
    }

    fun getSingleDoctorsSchedule(fio: String): Observable<OfficeHoursJS>{
        val docFIO = getDoctorFIO(fio)
       return retrofitServiceInterfaceSchedule.patientOfficeHoursRequestDoctors(docFIO)
    }

    private fun getDoctorFIO(fio: String): DoctorFIO{
        return DoctorFIO(fio)
    }

}