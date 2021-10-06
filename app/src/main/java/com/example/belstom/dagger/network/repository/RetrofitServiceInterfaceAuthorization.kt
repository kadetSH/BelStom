package com.example.belstom.dagger.network.repository

import com.example.belstom.jsonMy.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServiceInterfaceAuthorization {
    @POST("PasswordRequest2")
    fun passwordRequest(
        @Body searchClient: SearchClient
    ): Observable<PasswordRequest>

    @POST("AuthGetUI")
    fun authorization(
        @Body auth: Authorization
    ): Observable<IdRequest>

    @POST("DoctorsListRequest")
    fun getDoctorsList(
    ): Observable<ArrayList<DoctorsListJS>>
}