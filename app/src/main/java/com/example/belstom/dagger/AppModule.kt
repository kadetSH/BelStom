package com.example.belstom.dagger

import com.example.belstom.dagger.util.AndroidResourceProvider
import com.example.belstom.dagger.util.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [
    ViewModelModule::class
])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider
}