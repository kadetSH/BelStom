package com.example.belstom.dagger

import dagger.Module

@Module(includes = [
    ViewModelModule::class
])
class AppModule {

//    @Binds
//    @Singleton
//    fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider
}