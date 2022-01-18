package com.example.belstom


import com.example.belstom.dagger.DaggerAppComponent
import com.example.belstom.dagger.network.repository.*
import com.example.belstom.view.authorization.interactor.AuthorizationInteractor
import com.example.belstom.view.authorization.interactor.GetPasswordInteractor
import com.example.belstom.room.authorization.AuthorizationDao
import com.example.belstom.room.contactInformation.ContactInformationDao
import com.example.belstom.room.doctors.DoctorsDao
import com.example.belstom.room.news.NewsDao
import com.example.belstom.room.reception.ReceptionDao
import com.example.belstom.room.schedule.DepartmentScheduleDao
import com.example.belstom.room.visits.VisitsDao
import com.example.belstom.view.cabinet.news.interactor.NewsDescriptionInteractor
import com.example.belstom.view.cabinet.news.interactor.NewsInteractor
import com.example.belstom.view.cabinet.profile.interactor.ProfileInteractor
import com.example.belstom.view.cabinet.receptions.interactor.ReceptionInteractor
import com.example.belstom.view.cabinet.schedule.interactor.DepartmentReceptionDaysInteractor
import com.example.belstom.view.cabinet.visits.interactor.VisitsInteractor
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject


class App : DaggerApplication() {


    @Inject
    lateinit var retrofitServiceInterfaceAuthorization: RetrofitServiceInterfaceAuthorization

    @Inject
    lateinit var retrofitServiceInterfaceContactInformation: RetrofitServiceInterfaceContactInformation

    @Inject
    lateinit var retrofitServiceInterfaceNews: RetrofitServiceInterfaceNews

    @Inject
    lateinit var retrofitServiceInterfaceSchedule: RetrofitServiceInterfaceSchedule

    @Inject
    lateinit var retrofitServiceInterfaceReception: RetrofitServiceInterfaceReception

    @Inject
    lateinit var retrofitServiceInterfaceVisits: RetrofitServiceInterfaceVisits


    @Inject
    lateinit var authorizationDao: AuthorizationDao

    @Inject
    lateinit var contactInformationDao: ContactInformationDao

    @Inject
    lateinit var contactNewsDao: NewsDao

    @Inject
    lateinit var departmentScheduleDao: DepartmentScheduleDao

    @Inject
    lateinit var doctorsDao: DoctorsDao

    @Inject
    lateinit var receptionsDao: ReceptionDao

    @Inject
    lateinit var visitsDao: VisitsDao

    lateinit var authorizationInteractor: AuthorizationInteractor
    lateinit var getPasswordInteractor: GetPasswordInteractor
    lateinit var getProfileInteractor: ProfileInteractor
    lateinit var getNewsInteractor: NewsInteractor
    lateinit var getNewsDescriptionInteractor: NewsDescriptionInteractor
    lateinit var getDepartmentReceptionDaysInteractor: DepartmentReceptionDaysInteractor
    lateinit var getReceptionInteractor: ReceptionInteractor
    lateinit var getVisitsInteractor: VisitsInteractor

//    @Inject
//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        initInteractor()
    }

    private fun initInteractor() {
        authorizationInteractor = AuthorizationInteractor(
            retrofitServiceInterfaceAuthorization,
            authorizationDao,
            contactInformationDao,
            departmentScheduleDao,
            doctorsDao,
            receptionsDao,
            visitsDao
//            contactNewsDao,
//            visitHistoryDao,
//            xRaysDao,
//            picturesVisitDao,
//            radiationDoseDao,
//            newsDao
        )

        getPasswordInteractor = GetPasswordInteractor(
            retrofitServiceInterfaceAuthorization
        )

        getProfileInteractor = ProfileInteractor(
            retrofitServiceInterfaceContactInformation,
            authorizationDao,
            contactInformationDao
        )
        getNewsInteractor = NewsInteractor(
            retrofitServiceInterfaceNews,
            authorizationDao,
            contactInformationDao,
            contactNewsDao
        )

        getNewsDescriptionInteractor = NewsDescriptionInteractor(
            retrofitServiceInterfaceNews
        )

        getDepartmentReceptionDaysInteractor = DepartmentReceptionDaysInteractor(
            retrofitServiceInterfaceSchedule,
            departmentScheduleDao,
            authorizationDao,
            doctorsDao,
            visitsDao
        )

        getReceptionInteractor = ReceptionInteractor(
            retrofitServiceInterfaceReception,
            authorizationDao,
            receptionsDao
        )

        getVisitsInteractor = VisitsInteractor(
            retrofitServiceInterfaceVisits,
            authorizationDao,
            visitsDao
        )
    }

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()  //application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector


    companion object {
        lateinit var instance: App
            private set
    }

}