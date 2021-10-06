package com.example.belstom.view.authorization.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.belstom.App
import com.example.belstom.R
import com.example.belstom.SingleLiveEvent
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartGetPasswordViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val interactor = App.instance.getPasswordInteractor

    private var _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun getPassword(
        surname: String,
        name: String,
        patronymic: String,
        telephone: String,
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
        if (telephone == "") {
            _toastMessage.postValue(context.resources.getString(R.string.toast_password_telephone_filds))
            return
        }
        requestPassword(surname, name, patronymic, telephone, context)
    }


    @SuppressLint("CheckResult")
    private fun requestPassword(
        surname: String,
        name: String,
        patronymic: String,
        telephone: String,
        context: Context
    ) {
        val password = interactor.getPassword(surname, name, patronymic, telephone)
        password
            .subscribeOn(Schedulers.io())
            .doOnError {
//                _booleanAnimation.postValue(false)
                _toastMessage.postValue(context.resources.getString(R.string.toast_password_error_request))
            }
            .subscribe(
                { result ->
//                    _booleanAnimation.postValue(false)
                    if (result.result == context.resources.getString(R.string.toast_password_client_found)) {
                        _toastMessage.postValue(context.resources.getString(R.string.toast_password_sent))
                    } else {
                        _toastMessage.postValue(result.result)
                    }
                },
                { error ->
//                    _booleanAnimation.postValue(false)
                    _toastMessage.postValue(error.message)
                }
            )
    }


}