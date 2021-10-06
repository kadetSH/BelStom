package com.example.belstom.view.cabinet.schedule.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.R
import com.example.belstom.SingleLiveEvent
import com.example.belstom.room.doctors.RDoctors
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DepartmentsViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getDepartmentReceptionDaysInteractor
    var readAllDoctorsLiveData = interactor.getDoctorsLiveData()

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _doctorSearch = SingleLiveEvent<RDoctors>()
    val doctorSearch: LiveData<RDoctors> get() = _doctorSearch

    @SuppressLint("CheckResult")
    fun getDoctorsSchedule(fio: String, context: Context) {
        val doctor = interactor.checkFIO(fio)
        doctor
            .subscribeOn(Schedulers.io())
            .doOnError {
            }
            .subscribe(
                { result ->
                    if (result == null) {
                        _toastMessage.postValue(context.resources.getString(R.string.toast_check_doctors))
                    } else {
                        _doctorSearch.postValue(result)
                    }

                },
                { error ->
                }
            )
    }

}