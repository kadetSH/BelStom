package com.example.belstom.dagger.network.repository

import com.example.belstom.jsonMy.ImagePathJS
import com.example.belstom.jsonMy.JSNews
import com.example.belstom.jsonMy.PatientUIjs
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServiceInterfaceNews {

    @POST("LoaderNewsClient")
    fun loaderNewsRequest(
        @Body auth: PatientUIjs
    ): Observable<ArrayList<JSNews>>

    @POST("LoaderPicturesNews")
    fun loadNewsImage(
        @Body imagePath: ImagePathJS
    ): Observable<ResponseBody>
}