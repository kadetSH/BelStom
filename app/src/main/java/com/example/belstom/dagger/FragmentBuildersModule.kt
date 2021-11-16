package com.example.belstom.dagger

import com.example.belstom.view.authorization.fragment.StartAuthorizationFragment
import com.example.belstom.view.authorization.fragment.StartFragment
import com.example.belstom.view.authorization.fragment.StartGetPasswordFragment
import com.example.belstom.view.cabinet.feedback.fragment.FeedbackFragment
import com.example.belstom.view.cabinet.news.fragment.NewsDescriptionFragment
import com.example.belstom.view.cabinet.news.fragment.NewsFragment
import com.example.belstom.view.cabinet.profile.fragment.ProfileFragment
import com.example.belstom.view.cabinet.receptions.fragment.ReceptionDescriptionFragment
import com.example.belstom.view.cabinet.receptions.fragment.ReceptionFragment
import com.example.belstom.view.cabinet.schedule.fragment.BusinessHoursFragment
import com.example.belstom.view.cabinet.schedule.fragment.DepartmentReceptionDaysDoctorsFragment
import com.example.belstom.view.cabinet.schedule.fragment.DepartmentReceptionDaysFragment
import com.example.belstom.view.cabinet.schedule.fragment.DepartmentsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeStartAuthorizationFragment(): StartAuthorizationFragment

    @ContributesAndroidInjector
    abstract fun contributeStartGetPasswordFragment(): StartGetPasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsDescriptionFragment(): NewsDescriptionFragment

    @ContributesAndroidInjector
    abstract fun contributeDepartmentsFragment(): DepartmentsFragment

    @ContributesAndroidInjector
    abstract fun contributeDepartmentReceptionDaysFragment(): DepartmentReceptionDaysFragment

    @ContributesAndroidInjector
    abstract fun contributeBusinessHoursFragment(): BusinessHoursFragment

    @ContributesAndroidInjector
    abstract fun contributeDepartmentReceptionDaysDoctorsFragment(): DepartmentReceptionDaysDoctorsFragment

    @ContributesAndroidInjector
    abstract fun contributeFeedbackFragment(): FeedbackFragment

    @ContributesAndroidInjector
    abstract fun contributeReceptionFragment(): ReceptionFragment

    @ContributesAndroidInjector
    abstract fun contributeReceptionDescriptionFragment(): ReceptionDescriptionFragment


}