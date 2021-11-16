package com.example.belstom.view.authorization.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.belstom.dagger.util.ResourceProvider
import javax.inject.Inject

class StartViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {


    fun test() {
        println("qwewqewqewqewqewqe")
    }

}