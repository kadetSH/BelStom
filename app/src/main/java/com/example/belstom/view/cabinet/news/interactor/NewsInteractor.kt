package com.example.belstom.view.cabinet.news.interactor

import androidx.lifecycle.LiveData
import com.example.belstom.dagger.network.repository.RetrofitServiceInterfaceNews
import com.example.belstom.jsonMy.JSNews
import com.example.belstom.jsonMy.PatientUIjs
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.room.contactInformation.ContactInformationDao
import com.example.belstom.room.news.NewsDao
import com.example.belstom.room.news.RNews
import io.reactivex.Observable

class NewsInteractor(
    private val retrofitServiceInterfaceNews: RetrofitServiceInterfaceNews,
    private val authorizationDao: AuthorizationDao,
    private val contactInformationDao: ContactInformationDao,
    private val contactNewsDao: NewsDao
) {

    fun getNewsList(): Observable<ArrayList<JSNews>> {
        val firstLogin: RLogin = authorizationDao.getFirstLogin()
        val patientUIjs = getPatientUIjs(firstLogin.ui)
        return retrofitServiceInterfaceNews.loaderNewsRequest(patientUIjs)
    }

    private fun getPatientUIjs(patientUI: String): PatientUIjs {
        return PatientUIjs(patientUI)
    }

    fun checkNews(data: String, title: String, content: String, imagePath: String, newsId: String) {
        val news = contactNewsDao.getContact(newsId)
        if (news == null) {
            contactNewsDao.addNews(RNews(0, data, title, content, imagePath, newsId))
        } else {

            if (title != news.title) {
                updateTitle(newsId, title)
            }
            if (content != news.content) {
                updateContent(newsId, content)
            }
            if (imagePath != news.imagePath) {
                updateImagePath(newsId, imagePath)
            }

        }

    }

    private fun updateImagePath(newsId: String, imagePath: String) {
        contactNewsDao.updateImagePath(newsId, imagePath)
    }

    private fun updateContent(newsId: String, content: String) {
        contactNewsDao.updateContent(newsId, content)
    }

    private fun updateTitle(newsId: String, title: String) {
        contactNewsDao.updateTitle(newsId, title)
    }

    fun getNewsLiveData(): LiveData<List<RNews>> {
        return contactNewsDao.readAllNewsLiveData()
    }

}