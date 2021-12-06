package com.example.belstom.view.cabinet.visits.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.room.visits.RVisits
import javax.inject.Inject

class VisitsViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getVisitsInteractor
    val readAllVisitsLiveData: LiveData<List<RVisits>> = interactor.getVisitsLiveData()

    fun loadVisits(){
        interactor.loadVisitsList()
    }
}