package com.example.belstom.view.cabinet.receptions.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.SingleLiveEvent
import com.example.belstom.jsonMy.ReceptionDescriptionAnswer
import com.example.belstom.room.reception.RReception
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionItem
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReceptionDescriptionViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getReceptionInteractor

    private var _descriptionList = SingleLiveEvent<ArrayList<ReceptionDescriptionAnswer>>()
    val descriptionList: LiveData<ArrayList<ReceptionDescriptionAnswer>> get() = _descriptionList

    @SuppressLint("CheckResult")
    fun getDescription(receptionItem: ReceptionItem){
        interactor.getReceptionDescription(receptionItem)
            ?.subscribeOn(Schedulers.io())
            ?.doOnError {
                println("")
            }
            ?.subscribe(
                { result ->
                    _descriptionList.postValue(result)
                },
                { error ->
                    println("")
                }
            )
    }

}