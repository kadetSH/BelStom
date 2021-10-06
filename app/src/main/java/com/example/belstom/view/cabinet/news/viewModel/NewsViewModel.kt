package com.example.belstom.view.cabinet.news.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.SingleLiveEvent
import com.example.belstom.room.news.RNews
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getNewsInteractor

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _booleanAnimation = SingleLiveEvent<Boolean>()
    val booleanAnimation: LiveData<Boolean> get() = _booleanAnimation

    val readAllNewsLiveData: LiveData<List<RNews>> = interactor.getNewsLiveData()

    @SuppressLint("CheckResult")
    fun getNews(){
        val newsList = interactor.getNewsList()
        newsList
            ?.subscribeOn(Schedulers.io())
            ?.doOnError {
                println("")
            }
            ?.subscribe(
                { result ->
                    result.forEach {item ->
                        interactor.checkNews(item.data, item.title, item.content, item.imagePath, item.newsId)
                    }
                },
                { error ->
                    println("")
                }
            )
    }



}