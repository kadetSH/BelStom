package com.example.belstom.view.cabinet.schedule.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.Constants
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentBusinessHoursBinding
import com.example.belstom.jsonMy.BusinessHoursResultJS
import com.example.belstom.jsonMy.CreateAnAppointmentJS
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.businessHours.BusinessHoursAdapter
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.businessHours.BusinessHoursItem
import com.example.belstom.view.cabinet.schedule.viewModel.BusinessHoursViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BusinessHoursFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: BusinessHoursViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance(
            doctor: String?,
            date: String?,
            periodOfTime: String?,
            department: String?,
            singleBool: Boolean
        ): BusinessHoursFragment {
            val args = Bundle()
            args.putString(Constants.DOCTOR_REQUEST, doctor)
            args.putString(Constants.DATE_REQUEST, date)
            args.putString(Constants.PERIOD_OF_TIME_REQUEST, periodOfTime)
            args.putString(Constants.DEPARTMENT, department)
            args.putBoolean(Constants.SINGLE_BOOLEAN, singleBool)
            val fragment = BusinessHoursFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var doctorRequest: String? = null
    var dateRequest: String? = null
    var periodOfTimeRequest: String? = null
    var department: String? = null
    var patientUI: String? = null
    var singleBoolean: Boolean? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: BusinessHoursAdapter? = null
    private var list = ArrayList<BusinessHoursItem>()
    private var titleDoctor: TextView? = null
    private var dateOfReceipt: TextView? = null
    private lateinit var binding: FragmentBusinessHoursBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessHoursBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arg ->
            doctorRequest =
                arg.getString(Constants.DOCTOR_REQUEST)
            dateRequest =
                arg.getString(Constants.DATE_REQUEST)
            periodOfTimeRequest =
                arg.getString(Constants.PERIOD_OF_TIME_REQUEST)
            department =
                arg.getString(Constants.DEPARTMENT)
            singleBoolean =
                arg.getBoolean(Constants.SINGLE_BOOLEAN)
        }

        singleBoolean?.let { singlBool ->
            when (singlBool) {
                false -> setTitleFragment()
                true -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_single_time)
                )
            }
        }

        getPatientUI()
        init()
        initRecycler()
        observeViewModel()
        getBusinessHours()
    }

    private fun setTitleFragment() {
        department?.let { depName ->
            when (depName) {
                requireContext().getString(R.string.fragment_list_department_label_therapeutic) -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_therapeutic)
                )
                requireContext().getString(R.string.fragment_list_department_label_orthopedic) -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_orthopedic)
                )
                requireContext().getString(R.string.fragment_list_department_label_LPO) -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_LPO)
                )
                requireContext().getString(R.string.fragment_list_department_label_orthopedicLPO) -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_orthopedicLPO)
                )
                requireContext().getString(R.string.fragment_list_department_label_surgery) -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_surgery)
                )
                requireContext().getString(R.string.fragment_list_department_label_LOR) -> setTitle(
                    requireContext().getString(R.string.fragment_title_schedule_doctor_LOR)
                )
            }
        }
    }

    private fun setTitle(title: String) {
        (activity as? TitleController)?.setTitle(title)
    }

    private fun getPatientUI() {
        patientUI = viewModel.getUI()
    }

    private fun observeViewModel() {
        viewModel.listBusinessHours.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<ArrayList<BusinessHoursResultJS>> { itemOfficeHoursJS ->
                list.clear()
                itemOfficeHoursJS.forEach { item ->
                    list.add(BusinessHoursItem(item.time, item.access))
                }
                recyclerView?.adapter?.notifyDataSetChanged()
            })

        viewModel.toastMessage.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<String> { message ->
                selectToast(message)
            })

        viewModel.checkPeriodOfTime.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<CreateAnAppointmentJS> { request ->
                writeRequestAlertDialog(requireContext(), request)
            })
    }

    private fun init() {
        titleDoctor = binding.idFrBusinessHoursTitle
        dateOfReceipt = binding.idFrBusinessHoursDate
        doctorRequest?.let {
            titleDoctor!!.text = it
        }
        dateOfReceipt?.text = dateRequest

    }

    private fun getBusinessHours() {
        doctorRequest?.let { doctorString ->
            dateRequest?.let { dateString ->
                periodOfTimeRequest?.let { periodString ->
                    viewModel.getBusinessHours(
                        doctorString,
                        dateString, periodString
                    )
                }
            }
        }
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        adapter = BusinessHoursAdapter(
            LayoutInflater.from(requireContext()),
            list
        )
        { businessHoursItem: BusinessHoursItem, position: Int ->
            patientUI?.let { patientUIString ->
                doctorRequest?.let { doctorRequestString ->
                    dateRequest?.let { dateRequestString ->
//                        (activity as? OnBusinessHoursClickListener)?.onBusinessClick(
//                            businessHoursItem,
//                            position,
//                            patientUIString,
//                            doctorRequestString,
//                            dateRequestString
//                        )
                        viewModel.clickTimeItem(
                            requireContext(),
                            patientUIString,
                            doctorRequestString,
                            dateRequestString,
                            businessHoursItem.periodOfTime,
                            businessHoursItem.time
                        )
                    }
                }
            }
        }
        recyclerView = binding.idRecyclerViewBusinessHours
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    private fun writeRequestAlertDialog(
        context: Context,
        anAppointmentJS: CreateAnAppointmentJS
    ) {
        val builderAlertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        val clickCancel = DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        }
        val clickRecording = DialogInterface.OnClickListener { dialog, which ->
            viewModel.makeToAppointment(anAppointmentJS)
        }
        builderAlertDialog.setMessage(resources.getString(R.string.toast_make_an_appointment))
        builderAlertDialog.setNegativeButton(resources.getString(R.string.labelNo), clickCancel)
        builderAlertDialog.setPositiveButton(resources.getString(R.string.labelYes), clickRecording)
        val dialog: AlertDialog = builderAlertDialog.create()
        dialog.show()
    }

    private fun selectToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}