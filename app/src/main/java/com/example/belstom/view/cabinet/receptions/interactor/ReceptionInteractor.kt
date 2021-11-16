package com.example.belstom.view.cabinet.receptions.interactor

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceReception
import com.example.belstom.jsonMy.PatientUIjs
import com.example.belstom.jsonMy.ReceptionDescriptionAnswer
import com.example.belstom.jsonMy.ReceptionJS
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.news.RNews
import com.example.belstom.room.reception.RReception
import com.example.belstom.room.reception.ReceptionDao
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ReceptionInteractor(
    private val retrofitServiceInterfaceReception: RetrofitServiceInterfaceReception,
    private val authorizationDao: AuthorizationDao,
    private val receptionsDao: ReceptionDao
) {

    fun getReceptionDescription(receptionItem: ReceptionItem): Observable<ArrayList<ReceptionDescriptionAnswer>> {
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        val receptionJS = ReceptionJS(firstLogin.ui, receptionItem.doctor, receptionItem.number)
        return retrofitServiceInterfaceReception.getListReceptionDescription(receptionJS)
    }

    fun getReceptionLiveData(): LiveData<List<RReception>> {
        return receptionsDao.readAllReceptionLiveData()
    }

    @SuppressLint("CheckResult")
    fun getReceptionListOfServer() {
        val list = receptionsDao.readAllReception()
        val count = list.size
        if (count == 0) {
            val uiJS = getUiPatient()
            retrofitServiceInterfaceReception.getListReceptionPatient(uiJS)
                ?.subscribeOn(Schedulers.io())
                ?.doOnError {
                    println("")
                }
                ?.subscribe(
                    { result ->
                        result.forEach { item ->
                            receptionsDao.addReception(
                                RReception(
                                    0,
                                    item.receptionNumber,
                                    item.title,
                                    item.dateOfReceipt,
                                    item.doctor,
                                    item.cavity
                                )
                            )
                        }
                    },
                    { error ->
                        println("")
                    }
                )
        }
    }

    private fun getUiPatient(): PatientUIjs {
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        return getPatientUIjs(firstLogin.ui)
    }

    private fun getPatientUIjs(patientUI: String): PatientUIjs {
        return PatientUIjs(patientUI)
    }


}