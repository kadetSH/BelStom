package com.example.belstom.dagger.network.repository

import com.example.belstom.jsonMy.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServiceInterfaceVisits {

    @POST("VisitsList")
    fun getVisitsList(
        @Body auth: PatientUIjs
    ): Observable<ArrayList<VisitsJS>>

}