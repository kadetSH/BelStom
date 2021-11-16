package com.example.belstom.view.authorization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.CheckClickView
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentStartBinding
import com.example.belstom.view.authorization.viewModel.StartViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class StartFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: StartViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentStartBinding
    private lateinit var labelSetPassword: TextView
    private lateinit var btnAuthorization: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_start_fragment)
        )
        init()

    }

    private fun init() {
        labelSetPassword = binding.frEditReg
        labelSetPassword.setOnClickListener {
            (activity as? CheckClickView)?.setSignature(labelSetPassword.text.toString())
        }
        btnAuthorization = binding.frStartBtn
        btnAuthorization.setOnClickListener {
            (activity as? CheckClickView)?.setSignature(btnAuthorization.text.toString())
        }
    }


}