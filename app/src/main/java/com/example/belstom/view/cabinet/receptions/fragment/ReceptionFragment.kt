package com.example.belstom.view.cabinet.receptions.fragment

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
import com.example.belstom.databinding.FragmentReceptionBinding
import com.example.belstom.room.reception.RReception
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionAdapter
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionItem
import com.example.belstom.view.cabinet.receptions.viewModel.ReceptionViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ReceptionFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ReceptionViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentReceptionBinding
    private var adapter: ReceptionAdapter? = null
    private var list = ArrayList<ReceptionItem>()
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceptionBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_reception)
        )
        initRecycler()
        viewModel.loadReception()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.readAllReceptionLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<List<RReception>> { result ->
                list.clear()
                result.forEach { item ->
                    list.add(
                        ReceptionItem(
                            item.title,
                            item.dateOfReceipt,
                            item.doctor,
                            item.cavity,
                            item.receptionNumber
                        )
                    )
                }
                recyclerView?.adapter?.notifyDataSetChanged()
            })
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        adapter = ReceptionAdapter(
            LayoutInflater.from(requireContext()),
            list
        ){item: ReceptionItem, position: Int ->
            (activity as? ReceptionFragment.OpenReceptionItem)?.openReceptionDescriptionItem(item)
        }
        recyclerView = binding.idRecyclerViewReception
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    interface OpenReceptionItem{
        fun openReceptionDescriptionItem(receptionItem: ReceptionItem)
    }

}