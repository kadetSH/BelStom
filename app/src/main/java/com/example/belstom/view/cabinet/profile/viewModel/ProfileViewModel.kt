package com.example.belstom.view.cabinet.profile.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.SingleLiveEvent
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.contactInformation.RContactInformation
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {


    private val interactor = App.instance.getProfileInteractor

    private var _login = SingleLiveEvent<RLogin>()
    val login: LiveData<RLogin> get() = _login

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _booleanAnimation = SingleLiveEvent<Boolean>()
    val booleanAnimation: LiveData<Boolean> get() = _booleanAnimation

    var readAllContactInfoLiveData: LiveData<List<RContactInformation>> = interactor.getContactInformationLiveData()

    @SuppressLint("CheckResult")
    fun getPatientInfo() {
        interactor.getContactInformationIsBase()
            ?.subscribeOn(Schedulers.io())
            ?.doOnError {
                println("")
            }
            ?.subscribe(
                { result ->
                    if (result.surname != "") {
                        interactor.updateContactInformation(
                            result.surname,
                            result.name,
                            result.patronymic,
                            result.birth,
                            result.age,
                            result.gender,
                            result.telephone,
                            result.address,
                            result.id1c
                        )
                    }
                },
                { error ->
                    println("")
                }
            )
    }


}