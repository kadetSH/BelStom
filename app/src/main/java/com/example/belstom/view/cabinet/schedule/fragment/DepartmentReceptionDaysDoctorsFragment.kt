package com.example.belstom.view.cabinet.schedule.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.Constants
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentDepartmentReceptionDaysBinding
import com.example.belstom.databinding.FragmentDepartmentReceptionDaysDoctorsBinding
import com.example.belstom.jsonMy.OfficeHoursJS
import com.example.belstom.room.contactInformation.RContactInformation
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.adapters.DayParentAdapterOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayChildModelOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayParentDataFactoryOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayParentModelOfDoctors
import com.example.belstom.view.cabinet.schedule.viewModel.DepartmentReceptionDaysDoctorsViewModel
import com.example.belstom.view.cabinet.schedule.viewModel.DepartmentReceptionDaysViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DepartmentReceptionDaysDoctorsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DepartmentReceptionDaysDoctorsViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance(doctor: String?): DepartmentReceptionDaysDoctorsFragment {
            val args = Bundle()
            args.putString(Constants.DOCTOR_SEARCH, doctor)
            val fragment = DepartmentReceptionDaysDoctorsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentDepartmentReceptionDaysDoctorsBinding
    private var recyclerView: RecyclerView? = null
    private var adapter: DayParentAdapterOfDoctors? = null
    var fioDoctor: String? = null
    private var listDoctors = ArrayList<OfficeHoursJS>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartmentReceptionDaysDoctorsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arg ->
            fioDoctor = arg.getString(Constants.DOCTOR_SEARCH)
        }
        fioDoctor?.let { fio ->
            viewModel.loadDoctorSchedule(fio)
        }
        (activity as? TitleController)?.setTitle(requireContext().resources.getString(R.string.fragment_title_schedule_doctor_single))

        init()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.officeHours.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<OfficeHoursJS> { result ->
                listDoctors.clear()
                listDoctors.add(result)
                updateAdapter(listDoctors)
            })
    }

    @SuppressLint("WrongConstant")
    private fun init() {
        recyclerView = binding.idFrDepartmentReceptionDaysDoctorRv

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
        adapter = DayParentAdapterOfDoctors(
            DayParentDataFactoryOfDoctors.getParents(
                listDoctors
            )
        ) { parentModel: DayParentModelOfDoctors, childModel: DayChildModelOfDoctors ->
            (activity as? DepartmentReceptionDaysFragment.OnDepartmentReceptionDaysClickListener)?.onDayClick(
                parentModel.fio,
                childModel.date,
                childModel.reception,
                "",
                true
            )

        }
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    @SuppressLint("WrongConstant")
    private fun updateAdapter(list: ArrayList<OfficeHoursJS>) {
        val adapter2 = DayParentAdapterOfDoctors(
            DayParentDataFactoryOfDoctors.getParents(
                list
            )
        ) { parentModel: DayParentModelOfDoctors, childModel: DayChildModelOfDoctors ->
            (activity as? DepartmentReceptionDaysFragment.OnDepartmentReceptionDaysClickListener)?.onDayClick(
                parentModel.fio,
                childModel.date,
                childModel.reception,
                "",
                true
            )
        }
        recyclerView?.adapter = adapter2
        recyclerView?.adapter?.notifyDataSetChanged()
    }


}