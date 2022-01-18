package com.example.belstom.view.cabinet.schedule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.Constants
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentAppointmentCreatedBinding
import com.example.belstom.jsonMy.AppointmentCreatedJS
import com.example.belstom.view.cabinet.schedule.viewModel.AppointmentCreatedViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AppointmentCreatedFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AppointmentCreatedViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance(
            resultCreated: AppointmentCreatedJS?
        ): AppointmentCreatedFragment {
            val args = Bundle()
            args.putSerializable(Constants.ANSWER_APPOINTMENT, resultCreated)
            val fragment = AppointmentCreatedFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var answer: AppointmentCreatedJS? = null
    private lateinit var binding: FragmentAppointmentCreatedBinding
    private var editData: TextView? = null
    private var editTime: TextView? = null
    private var editProfession: TextView? = null
    private var editDoctor: TextView? = null
    private var btnAllAppointment: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentCreatedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arg ->
            answer = arg.getSerializable(Constants.ANSWER_APPOINTMENT) as AppointmentCreatedJS?
        }
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_appointment_created_title)
        )
        init()
        answer?.let { ans ->
            editData?.text = ans.data
            editTime?.text = ans.time
            editProfession?.text = ans.profession
            editDoctor?.text = ans.doctor
        }
    }

    private fun init() {
        editData = binding.fragmentAppointmentCreatedEditData
        editTime = binding.fragmentAppointmentCreatedEditTime
        editProfession = binding.fragmentAppointmentCreatedEditProfession
        editDoctor = binding.fragmentAppointmentCreatedEditDoctor
        btnAllAppointment = binding.fragmentAppointmentCreatedBtnAllAppointment
        btnAllAppointment?.setOnClickListener {
            (activity as? OnAppointmentCreated)?.onClickAllAppointment(

            )
        }
    }

    interface OnAppointmentCreated{
        fun onClickAllAppointment()
    }
}