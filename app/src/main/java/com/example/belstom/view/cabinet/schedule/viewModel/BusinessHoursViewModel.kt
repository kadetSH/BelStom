package com.example.belstom.view.cabinet.schedule.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.R
import com.example.belstom.SingleLiveEvent
import com.example.belstom.jsonMy.BusinessHoursResultJS
import com.example.belstom.jsonMy.CreateAnAppointmentJS
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BusinessHoursViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _checkPeriodOfTime = SingleLiveEvent<CreateAnAppointmentJS>()
    val checkPeriodOfTime: LiveData<CreateAnAppointmentJS> get() = _checkPeriodOfTime

    private var _listBusinessHours = SingleLiveEvent<ArrayList<BusinessHoursResultJS>>()
    val listBusinessHours: LiveData<ArrayList<BusinessHoursResultJS>> get() = _listBusinessHours

    private val interactor = App.instance.getDepartmentReceptionDaysInteractor

    @SuppressLint("CheckResult")
    fun getBusinessHours(
        doctorRequest: String,
        dateRequest: String,
        periodOfTimeRequest: String
    ) {
        interactor.getBusinessHours(doctorRequest, dateRequest, periodOfTimeRequest)
            .subscribeOn(Schedulers.io())
            .doOnError {
                _toastMessage.postValue(it.toString())
            }
            .subscribe(
                { result ->
                    _listBusinessHours.postValue(result)
                },
                { error ->
                    _toastMessage.postValue(error.toString())
                }
            )
    }

    fun getUI(): String {
      return  interactor.getPatientUI()
    }

    fun clickTimeItem(
        requireContext: Context,
        patientUIString: String,
        doctorRequestString: String,
        dateRequestString: String,
        periodOfTime: Int,
        time: String
    ) {
        if (periodOfTime == 0) {
            _toastMessage.postValue(requireContext.resources.getString(R.string.toast_recording_time_is_busy))
        } else {
            val anAppointment = CreateAnAppointmentJS(patientUIString, doctorRequestString, dateRequestString, time)
            _checkPeriodOfTime.postValue(anAppointment)
        }
    }

    @SuppressLint("CheckResult")
    fun makeToAppointment(anAppointmentJS: CreateAnAppointmentJS) {
        interactor.makeToAppointment(anAppointmentJS)
            .subscribeOn(Schedulers.io())
            .doOnError {
                _toastMessage.postValue(it.toString())
            }
            .subscribe(
                { result ->
                    _toastMessage.postValue(result.answer)
                },
                { error ->
                    _toastMessage.postValue(error.toString())
                }
            )
    }


}