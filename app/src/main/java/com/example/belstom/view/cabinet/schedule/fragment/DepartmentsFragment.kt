package com.example.belstom.view.cabinet.schedule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentDepartments2Binding
import com.example.belstom.room.doctors.RDoctors
import com.example.belstom.view.cabinet.schedule.viewModel.DepartmentsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class DepartmentsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DepartmentsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentDepartments2Binding
    private var buttTherapeutic: TextView? = null
    private var buttOrthopedic: TextView? = null
    private var buttLPO: TextView? = null
    private var buttSurgery2: TextView? = null
    private var buttSurgery1: TextView? = null
    private var buttLOR: TextView? = null
    private var autoCompleteTextView: AutoCompleteTextView? = null
    private var btnSearch: Button? = null
    private var buttSpecialityOrthopedic: TextView? = null
    private var buttSpecialityTherapeutic: TextView? = null
    private var buttSpecialitySurgery: TextView? = null
    private var buttSpecialityPeriodontist: TextView? = null
    private var buttSpecialityImplantation: TextView? = null
    private var buttSpecialityHygienist: TextView? = null
    private var buttSpecialityOrthodontist: TextView? = null
    private var buttSpecialityLor: TextView? = null
    private var countries = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartments2Binding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_department_title)
        )
        init()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.readAllDoctorsLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<List<RDoctors>> { result ->
                result.forEach { item ->
                    countries.add(item.fio)
                }
                adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.custom_list_item,
                    R.id.text_view_list_item,
                    countries
                )
                autoCompleteTextView?.setAdapter(adapter)
            })

        viewModel.toastMessage.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<String> { message ->
                selectToast(message)
            })

        viewModel.doctorSearch.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<RDoctors> { doc ->
                (activity as? OnRequestDoctorsSchedule)?.onRequestDoctorsSchedule(
                    doc.fio
                )
            })
    }

    private fun selectToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun init() {
        buttTherapeutic = binding.fragmentListDepartmentLabelTherapeutic
        buttLPO = binding.fragmentListDepartmentLabelLPO
        buttSurgery1 = binding.fragmentListDepartmentLabelSurgery1
        buttSurgery2 = binding.fragmentListDepartmentLabelSurgery2
        buttOrthopedic = binding.fragmentListDepartmentLabelOrthopedic
        buttLOR = binding.fragmentListDepartmentLabelLOR


        buttSpecialityOrthopedic = binding.fragmentListDepartmentSpecialityOrthopedic
        buttSpecialityTherapeutic = binding.fragmentListDepartmentSpecialityTherapeutic
        buttSpecialitySurgery = binding.fragmentListDepartmentSpecialitySurgery
        buttSpecialityPeriodontist = binding.fragmentListDepartmentSpecialityPeriodontist
        buttSpecialityImplantation = binding.fragmentListDepartmentSpecialityImplantation
        buttSpecialityHygienist = binding.fragmentListDepartmentSpecialityHygienist
        buttSpecialityOrthodontist = binding.fragmentListDepartmentSpecialityOrthodontist
        buttSpecialityLor = binding.fragmentListDepartmentSpecialityLor

        autoCompleteTextView = binding.fragmentListDepartmentActv
        btnSearch = binding.fragmentListDepartmentBtnSearch

        btnSearch?.setOnClickListener {
            val fioDoctor = autoCompleteTextView?.text.toString()
            viewModel.getDoctorsSchedule(fioDoctor, requireContext())
        }

        buttTherapeutic?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_department_title_department_therapeutic)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttLPO?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_department_title_department_LPO)?.let { it1 ->
                (activity as? OnClickViewDepartment)?.onClickDepartment(
                    it1
                )
            }
        }

        buttSurgery1?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_department_title_department_surgery1)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttSurgery2?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_department_title_department_surgery2)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttOrthopedic?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_department_title_department_orthopedic)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttLOR?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_department_title_department_LOR)?.let { it1 ->
                (activity as? OnClickViewDepartment)?.onClickDepartment(
                    it1
                )
            }
        }

        buttSpecialityOrthopedic?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_orthopedic_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialityTherapeutic?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_therapeutic_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialitySurgery?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_surgery_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialityPeriodontist?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_periodontist_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialityImplantation?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_implantation_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialityHygienist?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_hygienist_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialityOrthodontist?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_orthodontist_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }
        buttSpecialityLor?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_LOR_speciality)
                ?.let {
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it
                    )
                }
        }

    }

    interface OnClickViewDepartment {
        fun onClickDepartment(name: String)
    }

    interface OnRequestDoctorsSchedule {
        fun onRequestDoctorsSchedule(fioDoctor: String)
    }


}