package com.example.belstom.view.authorization.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.belstom.App
import com.example.belstom.R
import com.example.belstom.SingleLiveEvent
import com.example.belstom.jsonMy.Authorization
import com.example.belstom.jsonMy.IdRequest
import com.example.belstom.room.authorization.RLogin
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartAuthorizationViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.authorizationInteractor

    private var _login = SingleLiveEvent<RLogin>()
    val login: LiveData<RLogin> get() = _login

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _booleanAnimation = SingleLiveEvent<Boolean>()
    val booleanAnimation: LiveData<Boolean> get() = _booleanAnimation

    private var _universalIdentifier = SingleLiveEvent<String>()
    val universalIdentifier: LiveData<String> get() = _universalIdentifier

    fun checkLogin(){

        val login  = interactor.getLogin()
            ?.subscribeOn(Schedulers.io())
            ?.doOnError {

            }
            ?.subscribe({ result ->
                _login.postValue(result)
            }, { error ->

            })
    }

    @SuppressLint("CheckResult")
    fun onLoginBtnClick(
        surname: String,
        name: String,
        patronymic: String,
        password: String,
        context: Context
    ) {
        if (surname == "") {
            _toastMessage.postValue(context.resources.getString(R.string.toast_password_surname_filds))
            return
        }
        if (name == "") {
            _toastMessage.postValue(context.resources.getString(R.string.toast_password_name_filds))
            return
        }
        if (patronymic == "") {
            _toastMessage.postValue(context.resources.getString(R.string.toast_password_patronymic_filds))
            return
        }
        if (password == "") {
            _toastMessage.postValue(context.resources.getString(R.string.toast_authorization_password_filds))
            return
        }
        _booleanAnimation.postValue(true)
        val json = getAuthorization(surname, name, patronymic, password)


        val result: io.reactivex.Observable<IdRequest> = interactor.getAuthorization(json)
        result
            .subscribeOn(Schedulers.io())
            .doOnError {
                _booleanAnimation.postValue(false)
                _toastMessage.postValue(context.resources.getString(R.string.toast_authorization_error_request))
            }
            .subscribe({ result ->
                if (result.result == context.resources.getString(R.string.toast_authorization_id_found)) {
                    interactor.reconciliationOfUsers(
                        json.surname,
                        json.name,
                        json.patronymic,
                        json.password,
                        result.id
                    )
                    _booleanAnimation.postValue(false)
                    _universalIdentifier.postValue(result.id)
                    _toastMessage.postValue(result.result)
                    interactor.getDoctorsList()
                } else {
                    _booleanAnimation.postValue(false)
                    _toastMessage.postValue(result.result)
                }
            }, { error ->
                _booleanAnimation.postValue(false)
                _toastMessage.postValue(error.message)
            })
    }


    private fun getAuthorization(
        surname: String,
        name: String,
        patronymic: String,
        password: String
    ): Authorization =
        Authorization(surname, name, patronymic, password)
}