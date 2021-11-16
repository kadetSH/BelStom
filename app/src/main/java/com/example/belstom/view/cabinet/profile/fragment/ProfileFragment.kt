package com.example.belstom.view.cabinet.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentProfileBinding
import com.example.belstom.room.contactInformation.RContactInformation
import com.example.belstom.view.cabinet.profile.viewModel.ProfileViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProfileViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var editTitle: TextView
    private lateinit var editID: TextView
    private lateinit var editGender: TextView
    private lateinit var editName: TextView
    private lateinit var editSurname: TextView
    private lateinit var editPatronymic: TextView
    private lateinit var editTelephone: TextView
    private lateinit var editBirth: TextView
    private lateinit var editGender2: TextView
    private lateinit var editAddress: TextView
    private lateinit var btnMyMap: Button
    private lateinit var btnTreatmentPlan: Button
    private lateinit var btnExit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_profile)
        )
        init()
        viewModel.getPatientInfo()
        observeViewModel()

    }

    private fun init() {
        editTitle = binding.frProfileTitle
        editID = binding.frProfileEditId
        editGender = binding.frProfileEditGender
        editName = binding.frProfileEditName
        editSurname = binding.frProfileEditSurname
        editPatronymic = binding.frProfileEditPatronymic
        editTelephone = binding.frProfileEditTelephone
        editBirth = binding.frProfileEditBirth
        editGender2 = binding.frProfileEditGender2
        editAddress = binding.frProfileEditAddress
        btnMyMap = binding.frProfileBtnMyMap
        btnMyMap.setOnClickListener {
            (activity as? OpenMyMap)?.clickOpenMyMap()
        }
        btnTreatmentPlan = binding.frProfileBtnTreatmentPlan
        btnExit = binding.frProfileBtnExit
        btnExit.setOnClickListener {
            (activity as? ExitCabinet)?.clickExitCabinet()
        }
    }

    private fun observeViewModel() {
        viewModel.readAllContactInfoLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<List<RContactInformation>> { result ->
                result.forEach { patientInfo ->
                    editTitle.text = "${patientInfo.name} ${patientInfo.surname}"
                    editID.text = patientInfo.id1c
                    editGender.text = "${patientInfo.gender}, ${patientInfo.age}"
                    editName.text = patientInfo.name
                    editSurname.text = patientInfo.surname
                    editPatronymic.text = patientInfo.patronymic
                    editTelephone.text = patientInfo.telephone
                    editBirth.text = patientInfo.birth
                    editGender2.text = patientInfo.gender
                    editAddress.text = patientInfo.address
                }
            })
    }

    interface ExitCabinet{
        fun clickExitCabinet()
    }

    interface OpenMyMap{
        fun clickOpenMyMap()
    }

}