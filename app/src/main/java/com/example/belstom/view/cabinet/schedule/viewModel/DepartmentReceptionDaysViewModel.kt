package com.example.belstom.view.cabinet.schedule.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.SingleLiveEvent
import com.example.belstom.jsonMy.OfficeHoursJS
import com.example.belstom.room.news.RNews
import com.example.belstom.room.schedule.RDepartmentSchedule
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DepartmentReceptionDaysViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getDepartmentReceptionDaysInteractor
    lateinit var readAllDepartmentSchedule: LiveData<List<RDepartmentSchedule>>

    fun initDepartment(department: String){
        readAllDepartmentSchedule = interactor.getLiveDate(department)
    }


    @SuppressLint("CheckResult")
    fun getListDoctorsOfDepartment(department: String) {
        interactor.getListDoctors(department)
    }



}