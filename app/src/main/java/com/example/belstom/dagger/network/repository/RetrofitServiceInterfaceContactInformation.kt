package com.example.belstom.dagger.network.repository

import com.example.belstom.jsonMy.PatientData
import com.example.belstom.jsonMy.PatientUIjs
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServiceInterfaceContactInformation {
    @POST("PatientDataRequest2")
    fun patientDataRequest(
        @Body auth: PatientUIjs
    ): Observable<PatientData>
}