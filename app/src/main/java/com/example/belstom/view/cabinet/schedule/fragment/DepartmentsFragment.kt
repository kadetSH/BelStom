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
import com.example.belstom.databinding.FragmentDepartmentsBinding
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

    private lateinit var binding: FragmentDepartmentsBinding
    private var buttTherapeutic: TextView? = null
    private var buttOrthopedic: TextView? = null
    private var buttLPO: TextView? = null
    private var buttOrthopedicLPO: TextView? = null
    private var buttSurgery: TextView? = null
    private var buttLOR: TextView? = null
    private var autoCompleteTextView : AutoCompleteTextView? = null
    private var btnSearch: Button? = null
    private var countries  =  ArrayList<String>()
    var adapter  : ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartmentsBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_departments)
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
        buttOrthopedic = binding.fragmentListDepartmentLabelOrthopedic
        buttLPO = binding.fragmentListDepartmentLabelLPO
        buttOrthopedicLPO = binding.fragmentListDepartmentLabelOrthopedicLPO
        buttSurgery = binding.fragmentListDepartmentLabelSurgery
        buttLOR = binding.fragmentListDepartmentLabelLOR
        autoCompleteTextView = binding.fragmentListDepartmentActv
        btnSearch = binding.fragmentListDepartmentBtnSearch

        btnSearch?.setOnClickListener {
            val fioDoctor = autoCompleteTextView?.text.toString()
            viewModel.getDoctorsSchedule(fioDoctor, requireContext())
        }

        buttTherapeutic?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_therapeutic)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttOrthopedic?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_orthopedic)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttLPO?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_LPO)?.let { it1 ->
                (activity as? OnClickViewDepartment)?.onClickDepartment(
                    it1
                )
            }
        }

        buttOrthopedicLPO?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_orthopedicLPO)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttSurgery?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_surgery)
                ?.let { it1 ->
                    (activity as? OnClickViewDepartment)?.onClickDepartment(
                        it1
                    )
                }
        }

        buttLOR?.setOnClickListener {
            context?.resources?.getString(R.string.fragment_list_department_label_LOR)?.let { it1 ->
                (activity as? OnClickViewDepartment)?.onClickDepartment(
                    it1
                )
            }
        }
    }

    interface OnClickViewDepartment {
        fun onClickDepartment(name: String)
    }

    interface OnRequestDoctorsSchedule{
        fun onRequestDoctorsSchedule(fioDoctor: String)
    }


}