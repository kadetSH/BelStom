package com.example.belstom.view.cabinet.schedule.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.SingleLiveEvent
import com.example.belstom.jsonMy.OfficeHoursJS
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DepartmentReceptionDaysDoctorsViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getDepartmentReceptionDaysInteractor

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _officeHours = SingleLiveEvent<OfficeHoursJS>()
    val officeHours: LiveData<OfficeHoursJS> get() = _officeHours

    fun loadDoctorSchedule(fio: String){
       val schedule =  interactor.getSingleDoctorsSchedule(fio)
           .subscribeOn(Schedulers.io())
           .doOnError {
//               _toastMessage.postValue(it.toString())
           }
           .subscribe(
               { result ->
                   _officeHours.postValue(result)
               },
               { error ->
//                   _toastMessage.postValue(error.toString())
               }
           )
    }


}