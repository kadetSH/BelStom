package com.example.belstom.view.cabinet.feedback.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentFeedbackBinding
import com.example.belstom.databinding.FragmentNewsBinding
import com.example.belstom.view.cabinet.feedback.viewModel.FeedbackViewModel
import com.example.belstom.view.cabinet.news.viewModel.NewsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FeedbackFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FeedbackViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentFeedbackBinding
    private lateinit var btnWWW: ImageButton
    private lateinit var editWWW: TextView
    private lateinit var btnRegTelephone: ImageButton
    private lateinit var editRegTelephone: TextView
    private lateinit var btnEmail: ImageButton
    private lateinit var editEmail: TextView
    private lateinit var btnGeolocation: ImageButton
    private lateinit var editGeolocation: TextView
    private lateinit var btnVK: ImageView
    private lateinit var btnInstagram: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedbackBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_feedback)
        )

//        viewModel.getNews()
//        observeViewModel()
        init()
    }

    private fun init() {
        btnWWW = binding.frFeedbackBtnWww
        btnWWW.setOnClickListener { www ->
            (activity as? OpenFeedbackWWW)?.openWWW(editWWW.text.toString())
        }
        editWWW = binding.frFeedbackWwwEdit
        btnRegTelephone = binding.frFeedbackBtnTelephoneReg
        btnRegTelephone.setOnClickListener { regNumber ->
            (activity as? DialingPhoneNumber)?.dialingNumber(editRegTelephone.text.toString())
        }
        editRegTelephone = binding.frFeedbackTelephoneRegEdit
        btnEmail = binding.frFeedbackBtnEmail
        btnEmail.setOnClickListener {
            (activity as? SendLetter)?.sendLetter(editEmail.text.toString())
        }
        editEmail = binding.frFeedbackEmailEdit
        btnGeolocation = binding.frFeedbackBtnGeolocation
        btnGeolocation.setOnClickListener {
            val geolocation = "https://www.google.ru/maps/place/ул.+Преображенская,+56,+Белгород,+Белгородская+обл.,+308009/@50.5980994,36.5935071,17z/data=!3m1!4b1!4m5!3m4!1s0x41266a59c8b381cd:0xbca5b2cdcc2ca4a1!8m2!3d50.5980994!4d36.5956958?hl=ru&authuser=0"
            (activity as? OpenFeedbackWWW)?.openWWW(geolocation)
        }
        editGeolocation = binding.frFeedbackGeolocationEdit
        btnVK = binding.frFeedbackBtnVk
        btnVK.setOnClickListener {
            val vk = "https://vk.com/belstom_com"
            (activity as? OpenFeedbackWWW)?.openWWW(vk)
        }
        btnInstagram = binding.frFeedbackBtnInstagram
        btnInstagram.setOnClickListener {
            val instagram = "https://www.instagram.com/belstom/?hl=ru"
            (activity as? OpenFeedbackWWW)?.openWWW(instagram)
        }
    }


    interface OpenFeedbackWWW{
        fun openWWW(pathWWW: String)
    }

    interface DialingPhoneNumber{
        fun dialingNumber(number: String)
    }

    interface SendLetter{
        fun sendLetter(email: String)
    }


}