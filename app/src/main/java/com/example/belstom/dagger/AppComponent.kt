package com.example.belstom.dagger

import android.app.Application
import com.example.belstom.App
import com.example.belstom.dagger.network.NetworkModule
import com.example.belstom.dagger.room.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RoomModule::class,
        MainActivityModule::class,
        CabinetModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

//    fun resources(): ResourcesProvider

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)


    //Room
//    fun injectStartAuthorizationViewModel(contactInformationViewModel: StartAuthorizationViewModel)
//    fun injectAuthorizationActivation(activationDatabaseViewModel: ActivationDatabaseViewModel)
//    fun injectAuthorizationSetPassword(setPasswordViewModel: SetPasswordViewModel)
//    fun injectContracts(contractsViewModel: ContractsViewModel)
//    fun injectVisitHistory(visitHistoryViewModel: VisitHistoryViewModel)
//    fun injectXRays(xRaysViewModel: XRaysViewModel)
//    fun injectPicturesVisit(picturesVisitViewModel: PicturesVisitViewModel)
//    fun injectRadiationDose(radiationDoseViewModel: RadiationDoseViewModel)
//    fun injectNews(newsViewModel: NewsViewModel)

}