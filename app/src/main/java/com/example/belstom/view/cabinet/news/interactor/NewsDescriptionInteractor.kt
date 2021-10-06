package com.example.belstom.view.cabinet.news.interactor

import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceNews
import com.example.belstom.jsonMy.ImagePathJS
import io.reactivex.Observable
import okhttp3.ResponseBody

class NewsDescriptionInteractor(
    private val retrofitServiceInterfaceNews: RetrofitServiceInterfaceNews
) {

    fun loadImage(imagePath: String): Observable<ResponseBody> {
        val json = getImagePathJS(imagePath)
        return retrofitServiceInterfaceNews.loadNewsImage(json)
    }

    private fun getImagePathJS(imagePath: String): ImagePathJS {
        return ImagePathJS(imagePath)
    }

}