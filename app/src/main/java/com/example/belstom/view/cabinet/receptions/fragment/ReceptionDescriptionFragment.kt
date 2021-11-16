package com.example.belstom.view.cabinet.receptions.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.Constants
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentReceptionBinding
import com.example.belstom.databinding.FragmentReceptionDescriptionBinding
import com.example.belstom.jsonMy.ReceptionDescriptionAnswer
import com.example.belstom.room.reception.RReception
import com.example.belstom.view.cabinet.news.fragment.NewsDescriptionFragment
import com.example.belstom.view.cabinet.news.recyclerNews.NewsItem
import com.example.belstom.view.cabinet.receptions.recyclerReceptionDescription.ReceptionDescriptionAdapter
import com.example.belstom.view.cabinet.receptions.recyclerReceptionDescription.ReceptionDescriptionItem
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionAdapter
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionItem
import com.example.belstom.view.cabinet.receptions.viewModel.ReceptionDescriptionViewModel
import com.example.belstom.view.cabinet.receptions.viewModel.ReceptionViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ReceptionDescriptionFragment : DaggerFragment() {

    companion object {
        fun newInstance(receptionItem: ReceptionItem?): ReceptionDescriptionFragment {
            val args = Bundle()
            args.putSerializable(Constants.RECEPTION_ITEM, receptionItem)
            val fragment = ReceptionDescriptionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ReceptionDescriptionViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentReceptionDescriptionBinding
    private var adapter: ReceptionDescriptionAdapter? = null
    private var list = ArrayList<ReceptionDescriptionItem>()
    private var recyclerView: RecyclerView? = null
    var receptionItem: ReceptionItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceptionDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_reception_description)
        )
        arguments?.let { arg ->
            receptionItem = arg.getSerializable(Constants.RECEPTION_ITEM) as ReceptionItem?
        }
        receptionItem?.let { item ->
            initRecycler()
            viewModel.getDescription(item)
        observeViewModel()
        }

    }

    private fun observeViewModel() {
        viewModel.descriptionList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<ArrayList<ReceptionDescriptionAnswer>> { result ->
                list.clear()
                //list = ArrayList<ReceptionDescriptionItem>()
                result.forEach { item ->
                    list.add(
                        ReceptionDescriptionItem(
                            item.procedure,
                            item.cavity
                        )
                    )
                }
                recyclerView?.adapter?.notifyDataSetChanged()
            })
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        adapter = ReceptionDescriptionAdapter(
            LayoutInflater.from(requireContext()),
            list
        ) { item: ReceptionDescriptionItem, position: Int ->
            println("")
        }
        recyclerView = binding.idRecyclerViewReceptionDescription
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

}