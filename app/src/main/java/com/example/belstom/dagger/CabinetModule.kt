package com.example.belstom.dagger

import com.example.belstom.CabinetActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CabinetModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )
    abstract fun contributeCabinetActivity(): CabinetActivity
}