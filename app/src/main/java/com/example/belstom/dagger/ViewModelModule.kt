package com.example.belstom.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.view.authorization.viewModel.StartAuthorizationViewModel
import com.example.belstom.view.authorization.viewModel.StartGetPasswordViewModel
import com.example.belstom.view.authorization.viewModel.StartViewModel
import com.example.belstom.view.cabinet.feedback.viewModel.FeedbackViewModel
import com.example.belstom.view.cabinet.news.viewModel.NewsDescriptionViewModel
import com.example.belstom.view.cabinet.news.viewModel.NewsViewModel
import com.example.belstom.view.cabinet.profile.viewModel.ProfileViewModel
import com.example.belstom.view.cabinet.receptions.viewModel.ReceptionDescriptionViewModel
import com.example.belstom.view.cabinet.receptions.viewModel.ReceptionViewModel
import com.example.belstom.view.cabinet.schedule.viewModel.*
import com.example.belstom.view.cabinet.visits.viewModel.VisitsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun bindStartFragmentViewModel(viewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartAuthorizationViewModel::class)
    abstract fun bindStartAuthorizationViewModel(favoritesFilmsViewModel: StartAuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartGetPasswordViewModel::class)
    abstract fun bindStartGetPasswordViewModel(favoritesFilmsViewModel: StartGetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(favoritesFilmsViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(favoritesFilmsViewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDescriptionViewModel::class)
    abstract fun bindNewsDescriptionViewModel(favoritesFilmsViewModel: NewsDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DepartmentReceptionDaysViewModel::class)
    abstract fun bindDepartmentReceptionDaysViewModel(favoritesFilmsViewModel: DepartmentReceptionDaysViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BusinessHoursViewModel::class)
    abstract fun bindBusinessHoursViewModel(favoritesFilmsViewModel: BusinessHoursViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DepartmentsViewModel::class)
    abstract fun bindDepartmentsViewModel(favoritesFilmsViewModel: DepartmentsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DepartmentReceptionDaysDoctorsViewModel::class)
    abstract fun bindDepartmentReceptionDaysDoctorsViewModel(favoritesFilmsViewModel: DepartmentReceptionDaysDoctorsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedbackViewModel::class)
    abstract fun bindFeedbackViewModel(favoritesFilmsViewModel: FeedbackViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReceptionViewModel::class)
    abstract fun bindReceptionViewModel(favoritesFilmsViewModel: ReceptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReceptionDescriptionViewModel::class)
    abstract fun bindReceptionDescriptionViewModel(favoritesFilmsViewModel: ReceptionDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VisitsViewModel::class)
    abstract fun bindVisitsViewModel(favoritesFilmsViewModel: VisitsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppointmentCreatedViewModel::class)
    abstract fun bindAppointmentCreatedViewModel(favoritesFilmsViewModel: AppointmentCreatedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)