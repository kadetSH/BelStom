package com.example.belstom.view.cabinet.profile.interactor

import androidx.lifecycle.LiveData
import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceContactInformation
import com.example.belstom.jsonMy.PatientData
import com.example.belstom.jsonMy.PatientUIjs
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.contactInformation.ContactInformationDao
import com.example.belstom.room.contactInformation.RContactInformation

class ProfileInteractor(
    private val retrofitServiceInterfaceContactInformation: RetrofitServiceInterfaceContactInformation,
    private val authorizationDao: AuthorizationDao,
    private val contactInformationDao: ContactInformationDao
) {


    fun getContactInformationIsBase(
    ): io.reactivex.Observable<PatientData> {
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        val patientUIjs = getPatientUIjs(firstLogin.ui)
        return retrofitServiceInterfaceContactInformation.patientDataRequest(patientUIjs)
    }

    private fun getPatientUIjs(patientUI: String): PatientUIjs {
        return PatientUIjs(patientUI)
    }

    fun getContactInformationLiveData(): LiveData<List<RContactInformation>> {
        return contactInformationDao.readAllContractInfoLiveData()
    }

    fun updateContactInformation(
        surname: String,
        name: String,
        patronymic: String,
        birth: String,
        age: String,
        gender: String,
        telephone: String,
        address: String,
        id1c: String
    ) {
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        val contactInformation: RContactInformation =
            contactInformationDao.getContact(firstLogin.ui)
        if (contactInformation == null) {
            contactInformationDao.addContactInfo(
                RContactInformation(
                    0,
                    firstLogin.ui,
                    surname,
                    name,
                    patronymic,
                    birth,
                    age,
                    gender,
                    telephone,
                    address,
                    id1c
                )
            )
        }else{
            if (contactInformation.birth != birth) checkBirth(firstLogin.ui, birth)
            if (contactInformation.age != age) checkAge(firstLogin.ui, age)
            if (contactInformation.address != address) checkAddress(firstLogin.ui, address)
        }
    }

    private fun checkBirth(ui: String, birth: String){
        contactInformationDao.updateBirth(ui, birth)
    }

    private fun checkAge(ui: String, age: String){
        contactInformationDao.updateAge(ui, age)
    }

    private fun checkAddress(ui: String, address: String){
        contactInformationDao.updateAddress(ui, address)
    }

}