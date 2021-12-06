package com.example.belstom.view.cabinet.visits.interactor

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceVisits
import com.example.belstom.jsonMy.PatientUIjs
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.visits.RVisits
import com.example.belstom.room.visits.VisitsDao
import io.reactivex.schedulers.Schedulers

class VisitsInteractor(
    private val retrofitServiceInterfaceVisits: RetrofitServiceInterfaceVisits,
    private val authorizationDao: AuthorizationDao,
    private val visitsDao: VisitsDao
) {

    fun getVisitsLiveData(): LiveData<List<RVisits>>{
        return visitsDao.readAllVisitsLiveData()
    }

    @SuppressLint("CheckResult")
    fun loadVisitsList(){
        val count: Int = checkCountBase()
        if (count == 0) {
            val firstLogin: RLogin = authorizationDao.getFirstLogin()
            val patientUIjs = getPatientUIjs(firstLogin.ui)
            retrofitServiceInterfaceVisits.getVisitsList(patientUIjs)
                ?.subscribeOn(Schedulers.io())
                ?.doOnError {
                    println("")
                }
                ?.subscribe(
                    { result ->
                        result.forEach { item ->
                            visitsDao.addVisits(RVisits(0, item.title, item.date, item.doctor))
                        }
                    },
                    { error ->
                        println("")
                    }
                )
        }
    }

    private fun getPatientUIjs(patientUI: String): PatientUIjs {
        return PatientUIjs(patientUI)
    }

    private fun checkCountBase(): Int{
      return visitsDao.readAllVisits().size
    }


}