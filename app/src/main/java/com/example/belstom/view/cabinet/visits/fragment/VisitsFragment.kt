package com.example.belstom.view.cabinet.visits.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentVisitsBinding
import com.example.belstom.room.reception.RReception
import com.example.belstom.room.visits.RVisits
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionItem
import com.example.belstom.view.cabinet.visits.recyclerVisits.VisitsAdapter
import com.example.belstom.view.cabinet.visits.recyclerVisits.VisitsItem
import com.example.belstom.view.cabinet.visits.viewModel.VisitsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class VisitsFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: VisitsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentVisitsBinding
    private var adapter: VisitsAdapter? = null
    private var list = ArrayList<VisitsItem>()
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisitsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_visits)
        )
        initRecycler()
        viewModel.loadVisits()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.readAllVisitsLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<List<RVisits>> { result ->
                list.clear()
                result.forEach { item ->
                    list.add(
                        VisitsItem(
                            item.title,
                            item.date,
                            item.doctor
                        )
                    )
                }
                recyclerView?.adapter?.notifyDataSetChanged()
            })
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        adapter = VisitsAdapter(
            LayoutInflater.from(requireContext()),
            list
        ){item: VisitsItem, position: Int ->

        }
        recyclerView = binding.idRecyclerViewVisits
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

}