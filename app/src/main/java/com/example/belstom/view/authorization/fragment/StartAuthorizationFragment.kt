package com.example.belstom.view.authorization.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.CheckClickView
import com.example.belstom.R
import com.example.belstom.databinding.FragmentStartAuthorizationBinding
import com.example.belstom.jsonMy.ClientItem
import com.example.belstom.room.authorization.RLogin
import com.example.belstom.view.authorization.viewModel.StartAuthorizationViewModel
import dagger.android.support.DaggerFragment
import java.io.Serializable
import javax.inject.Inject

class StartAuthorizationFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: StartAuthorizationViewModel by viewModels {
        viewModelFactory
    }


    companion object {
        const val EXTRA_LOGIN = "EXTRA_LOGIN"
        fun newInstance(login: ClientItem?): StartAuthorizationFragment {
            val args = Bundle()
            args.putSerializable(EXTRA_LOGIN, login)
            val fragment = StartAuthorizationFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentStartAuthorizationBinding
    private lateinit var editSurname: TextView
    private lateinit var editName: TextView
    private lateinit var editPatronymic: TextView
    private lateinit var editPassword: TextView
    private lateinit var btnAuthorization: Button
    var clientItem: ClientItem? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartAuthorizationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arg ->
            clientItem = arg.getSerializable(EXTRA_LOGIN) as ClientItem
        }
        observeViewModel()
        init()

        if (clientItem == null) {
            viewModel.checkLogin()
        } else {
            editSurname.text = clientItem?.surname
            editName.text = clientItem?.name
            editPatronymic.text = clientItem?.patronymic
        }

    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.login.observe(viewLifecycleOwner, Observer<RLogin> { isLogin ->
            editSurname.text = isLogin.surname
            editName.text = isLogin.name
            editPatronymic.text = isLogin.patronymic
            editPassword.text = isLogin.password
        })

        viewModel.toastMessage.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<String> { message ->
                if (message == requireContext().resources.getString(R.string.toast_authorization_id_found)) {
                    (activity as? StartIntentCabinet)?.startIntentCabinet(
                        editSurname.text.toString(),
                        editName.text.toString(),
                        editPatronymic.text.toString()
                    )
                } else selectToast(message)
            })
    }

    private fun selectToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun init() {
        editSurname = binding.idFrStartAuthEditSurname as EditText
        editName = binding.idFrStartAuthEditName
        editPatronymic = binding.idFrStartAuthEditPatronymic
        editPassword = binding.idFrStartAuthEditPassword
        btnAuthorization = binding.idFrStartAuthButton
        btnAuthorization.setOnClickListener {
            viewModel.onLoginBtnClick(
                editSurname.text.toString(),
                editName.text.toString(),
                editPatronymic.text.toString(),
                editPassword.text.toString(),
                requireContext()
            )
        }
    }

    interface StartIntentCabinet {
        fun startIntentCabinet(surname: String, name: String, patronymic: String)
    }


}