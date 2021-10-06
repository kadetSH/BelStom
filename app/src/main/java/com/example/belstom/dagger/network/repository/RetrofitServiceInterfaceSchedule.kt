package com.example.belstom.dagger.network.repository

import com.example.belstom.jsonMy.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServiceInterfaceSchedule {

    @POST("OfficeHours2")
    fun patientOfficeHoursRequest(
        @Body auth: DepartmentJS2
    ): Observable<ArrayList<OfficeHoursJS>>

    @POST("OfficeHours2Doctors")
    fun patientOfficeHoursRequestDoctors(
        @Body auth: DoctorFIO
    ): Observable<OfficeHoursJS>

    @POST("DoctorRequests")
    fun businessHoursRequest(
        @Body auth: RequestDoctorRequestsJS
    ): Observable<ArrayList<BusinessHoursResultJS>>

    @POST("CreateAnAppointment")
    fun createAnAppointmentRequest(
        @Body auth: CreateAnAppointmentJS
    ): Observable<AnswerOfCreateAnAppointmentJS>

}