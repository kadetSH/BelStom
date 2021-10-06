package com.example.belstom.view.authorization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.R
import com.example.belstom.databinding.FragmentStartAuthorizationBinding
import com.example.belstom.databinding.FragmentStartGetPasswordBinding
import com.example.belstom.jsonMy.ClientItem
import com.example.belstom.view.authorization.viewModel.StartAuthorizationViewModel
import com.example.belstom.view.authorization.viewModel.StartGetPasswordViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StartGetPasswordFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: StartGetPasswordViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentStartGetPasswordBinding
    private lateinit var editSurname: TextView
    private lateinit var editName: TextView
    private lateinit var editPatronymic: TextView
    private lateinit var editTelephone: TextView
    private lateinit var btnRequest: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartGetPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
        init()


    }

    private fun init() {
        editSurname = binding.idFrStartGetPasswordEditSurname
        editName = binding.idFrStartGetPasswordEditName
        editPatronymic = binding.idFrStartGetPasswordEditPatronymic
        editTelephone = binding.idFrStartGetPasswordEditTelephone
        btnRequest = binding.idFrStartGetPasswordBtn
        btnRequest.setOnClickListener {
            viewModel.getPassword(
                editSurname.text.toString(),
                editName.text.toString(),
                editPatronymic.text.toString(),
                editTelephone.text.toString(),
                requireContext()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.toastMessage.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<String> { message ->
                selectToast(message)
                if (message == requireContext().resources.getString(R.string.toast_password_sent)) {
                    (activity as? OnBackPressedIsAuthorization)?.onBackPressedIsAuthorization(
                        ClientItem(
                            editSurname.text.toString(),
                            editName.text.toString(),
                            editPatronymic.text.toString()
                        )
                    )
                }

            })
    }

    private fun selectToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    interface OnBackPressedIsAuthorization {
        fun onBackPressedIsAuthorization(clientItem: ClientItem)
    }

}