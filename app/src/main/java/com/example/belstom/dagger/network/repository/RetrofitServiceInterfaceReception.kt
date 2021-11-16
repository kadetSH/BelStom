package com.example.belstom.dagger.network.repository

import com.example.belstom.jsonMy.PatientUIjs
import com.example.belstom.jsonMy.ReceptionDescriptionAnswer
import com.example.belstom.jsonMy.ReceptionJS
import com.example.belstom.jsonMy.ReceptionListJS
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServiceInterfaceReception {

    @POST("ReceptionList")
    fun getListReceptionPatient(
        @Body auth: PatientUIjs
    ): Observable<ArrayList<ReceptionListJS>>

    @POST("ReceptionDescription")
    fun getListReceptionDescription(
        @Body auth: ReceptionJS
    ): Observable<ArrayList<ReceptionDescriptionAnswer>>

}