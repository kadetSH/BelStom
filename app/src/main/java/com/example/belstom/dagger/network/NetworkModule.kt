package com.example.belstom.dagger.network

import com.example.belstom.BuildConfig
import com.example.belstom.dagger.network.repository.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val baseURL = BuildConfig.baseURL

    @Singleton
    @Provides
    fun getRetrofitInstance(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun getHttpClint(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }
                })
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterfaceAuthorization(retrofit: Retrofit): RetrofitServiceInterfaceAuthorization {
        return retrofit.create(RetrofitServiceInterfaceAuthorization::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterfaceContactInformation(retrofit: Retrofit): RetrofitServiceInterfaceContactInformation {
        return retrofit.create(RetrofitServiceInterfaceContactInformation::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterfaceNews(retrofit: Retrofit): RetrofitServiceInterfaceNews {
        return retrofit.create(RetrofitServiceInterfaceNews::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterfaceSchedule(retrofit: Retrofit): RetrofitServiceInterfaceSchedule {
        return retrofit.create(RetrofitServiceInterfaceSchedule::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterfaceReception(retrofit: Retrofit): RetrofitServiceInterfaceReception {
        return retrofit.create(RetrofitServiceInterfaceReception::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterfaceVisits(retrofit: Retrofit): RetrofitServiceInterfaceVisits {
        return retrofit.create(RetrofitServiceInterfaceVisits::class.java)
    }


}