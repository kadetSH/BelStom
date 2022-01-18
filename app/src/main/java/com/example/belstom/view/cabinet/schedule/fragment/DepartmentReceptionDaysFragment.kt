package com.example.belstom.view.cabinet.schedule.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.Constants
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentDepartmentReceptionDaysBinding
import com.example.belstom.jsonMy.OfficeHoursJS
import com.example.belstom.jsonMy.ScheduleJS
import com.example.belstom.room.news.RNews
import com.example.belstom.room.schedule.RDepartmentSchedule
import com.example.belstom.view.cabinet.news.recyclerNews.NewsItem
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.adapters.DayParentAdapterOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayChildModelOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayParentDataFactoryOfDoctors
import com.example.belstom.view.cabinet.schedule.recyclerSchedule.listDoctors.models.DayParentModelOfDoctors
import com.example.belstom.view.cabinet.schedule.viewModel.DepartmentReceptionDaysViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DepartmentReceptionDaysFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DepartmentReceptionDaysViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance(department: String?): DepartmentReceptionDaysFragment {
            val args = Bundle()
            args.putSerializable(Constants.DEPARTMENT, department)
            val fragment = DepartmentReceptionDaysFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentDepartmentReceptionDaysBinding
    private var recyclerView: RecyclerView? = null
    private var adapter: DayParentAdapterOfDoctors? = null
    var department: String? = null
    private var listDoctors = ArrayList<OfficeHoursJS>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartmentReceptionDaysBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let { arg ->
            department = arg.getString(Constants.DEPARTMENT)
        }
        department?.let {
            (activity as? TitleController)?.setTitle(it)
            viewModel.initDepartment(it)
        }

        init()
        observeViewModel()
        loadList()
    }

    @SuppressLint("WrongConstant")
    private fun observeViewModel() {
        viewModel.readAllDepartmentSchedule.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<List<RDepartmentSchedule>> { result ->
                val listOfficeHours = ArrayList<OfficeHoursJS>()
                result.forEach { item ->
                    val listSchedule = listOf(
                        ScheduleJS(item.dayDate0, item.dayStr0, item.reception0),
                        ScheduleJS(item.dayDate1, item.dayStr1, item.reception1),
                        ScheduleJS(item.dayDate2, item.dayStr2, item.reception2),
                        ScheduleJS(item.dayDate3, item.dayStr3, item.reception3),
                        ScheduleJS(item.dayDate4, item.dayStr4, item.reception4),
                        ScheduleJS(item.dayDate5, item.dayStr5, item.reception5),
                        ScheduleJS(item.dayDate6, item.dayStr6, item.reception6),
                        ScheduleJS(item.dayDate7, item.dayStr7, item.reception7),
                        ScheduleJS(item.dayDate8, item.dayStr8, item.reception8),
                        ScheduleJS(item.dayDate9, item.dayStr9, item.reception9),
                        ScheduleJS(item.dayDate10, item.dayStr10, item.reception10),
                        ScheduleJS(item.dayDate11, item.dayStr11, item.reception11),
                        ScheduleJS(item.dayDate12, item.dayStr12, item.reception12),
                        ScheduleJS(item.dayDate13, item.dayStr13, item.reception13),
                        ScheduleJS(item.dayDate14, item.dayStr14, item.reception14),
                        ScheduleJS(item.dayDate15, item.dayStr15, item.reception15),
                        ScheduleJS(item.dayDate16, item.dayStr16, item.reception16),
                        ScheduleJS(item.dayDate17, item.dayStr17, item.reception17),
                        ScheduleJS(item.dayDate18, item.dayStr18, item.reception18),
                        ScheduleJS(item.dayDate19, item.dayStr19, item.reception19),
                        ScheduleJS(item.dayDate20, item.dayStr20, item.reception20),
                        ScheduleJS(item.dayDate21, item.dayStr21, item.reception21),
                        ScheduleJS(item.dayDate22, item.dayStr22, item.reception22),
                        ScheduleJS(item.dayDate23, item.dayStr23, item.reception23),
                        ScheduleJS(item.dayDate24, item.dayStr24, item.reception24),
                        ScheduleJS(item.dayDate25, item.dayStr25, item.reception25),
                        ScheduleJS(item.dayDate26, item.dayStr26, item.reception26),
                        ScheduleJS(item.dayDate27, item.dayStr27, item.reception27),
                        ScheduleJS(item.dayDate28, item.dayStr28, item.reception28),
                        ScheduleJS(item.dayDate29, item.dayStr29, item.reception29),
                    )
                    listOfficeHours.add(OfficeHoursJS(item.doctor, item.profession, listSchedule))
                }
                updateAdapter(listOfficeHours)
            })
    }

    @SuppressLint("WrongConstant")
    private fun updateAdapter(list: ArrayList<OfficeHoursJS>) {
        val adapter2 = DayParentAdapterOfDoctors(
            DayParentDataFactoryOfDoctors.getParents(
                list
            )
        ) { parentModel: DayParentModelOfDoctors, childModel: DayChildModelOfDoctors ->
            if (childModel.reception == requireContext().getString(R.string.toast_no_reception)) {
                selectToast("${childModel.date} врач ${parentModel.fio} не принимает")
            } else {
                department?.let {
                    (activity as? OnDepartmentReceptionDaysClickListener)?.onDayClick(
                        parentModel.fio,
                        childModel.date,
                        childModel.reception,
                        it,
                        false
                    )
                }
            }
        }
        recyclerView?.adapter = adapter2
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun loadList() {
        department?.let {
            viewModel.getListDoctorsOfDepartment(it)
        }
    }

    @SuppressLint("WrongConstant")
    private fun init() {
        recyclerView = binding.idFrDepartmentReceptionDaysRv

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
        adapter = DayParentAdapterOfDoctors(
            DayParentDataFactoryOfDoctors.getParents(
                listDoctors
            )
        ) { parentModel: DayParentModelOfDoctors, childModel: DayChildModelOfDoctors ->

        }
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    private fun selectToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    interface OnDepartmentReceptionDaysClickListener {
        fun onDayClick(
            doctor: String,
            date: String,
            periodOfTime: String,
            department: String,
            singleBoolean: Boolean
        )
    }


}