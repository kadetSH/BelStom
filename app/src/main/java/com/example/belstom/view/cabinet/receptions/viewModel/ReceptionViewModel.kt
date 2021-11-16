package com.example.belstom.view.cabinet.receptions.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.room.reception.RReception
import javax.inject.Inject

class ReceptionViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getReceptionInteractor
    val readAllReceptionLiveData: LiveData<List<RReception>> = interactor.getReceptionLiveData()

    fun loadReception(){
        interactor.getReceptionListOfServer()
    }
}