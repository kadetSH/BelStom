package com.example.belstom.view.authorization.interactor

import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceAuthorization
import com.example.belstom.jsonMy.PasswordRequest
import com.example.belstom.jsonMy.SearchClient

class GetPasswordInteractor(
    private val retrofitServiceInterfaceAuthorization: RetrofitServiceInterfaceAuthorization
) {

    fun getPassword(
        surname: String,
        name: String,
        patronymic: String,
        telephone: String
    ): io.reactivex.Observable<PasswordRequest> {
        val client = getClient(surname, name, patronymic, telephone)
        return retrofitServiceInterfaceAuthorization.passwordRequest(client)
    }

    private fun getClient(
        surname: String,
        name: String,
        patronymic: String,
        telephone: String
    ): SearchClient {
        return SearchClient(surname, name, patronymic, telephone)
    }


}