package com.example.belstom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.belstom.jsonMy.ClientItem
import com.example.belstom.view.authorization.fragment.StartAuthorizationFragment
import com.example.belstom.view.authorization.fragment.StartFragment
import com.example.belstom.view.authorization.fragment.StartGetPasswordFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), CheckClickView,
    StartGetPasswordFragment.OnBackPressedIsAuthorization,
    StartAuthorizationFragment.StartIntentCabinet,
    TitleController{




    object Crashlytics {
        fun log(e: Throwable) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //Без этого лога не отправляется отчет об ошибках
        Crashlytics.log(IllegalArgumentException())
        if (savedInstanceState == null) {
            isActivation()
        }


        FirebaseMessaging.getInstance().subscribeToTopic("all")

       val qwe = intent.getStringExtra("titl")

        println("")
    }


    private fun isActivation() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainer, StartFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openFragmentStartAuthorization() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainer, StartAuthorizationFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openFragmentStartGetPassword() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainer, StartGetPasswordFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun setSignature(signature: String) {
        println("")
        when (signature) {
            resources.getString(R.string.fragment_start_btn) -> {
                openFragmentStartAuthorization()
            }
            resources.getString(R.string.fragment_start_edit_reg) -> {
                openFragmentStartGetPassword()
            }
        }
    }

    private fun startCabinetActivity(surname: String, name: String, patronymic: String) {
        finish()
        val intent = Intent(this, CabinetActivity::class.java)
        intent.putExtra(CabinetActivity.EXTRA_SURNAME, surname)
        intent.putExtra(CabinetActivity.EXTRA_NAME, name)
        intent.putExtra(CabinetActivity.EXTRA_PATRONYMIC, patronymic)
        startActivity(intent)
    }

    override fun onBackPressedIsAuthorization(clientItem: ClientItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainer, StartAuthorizationFragment.newInstance(clientItem))
            .addToBackStack(null)
            .commit()
    }

    override fun startIntentCabinet(surname: String, name: String, patronymic: String) {
        startCabinetActivity(surname, name, patronymic)
    }

    override fun onBackPressed() {
        when (title) {
            resources.getString(R.string.fragment_title_start_fragment) -> {
                finish()
            }
            resources.getString(R.string.fragment_title_get_password_fragment) -> {
                isActivation()
            }
            resources.getString(R.string.fragment_title_authorization_fragment) -> {
                isActivation()
            }
        }
    }

    override fun setTitle(titleIsFragment: String) {
        title = titleIsFragment
    }
}