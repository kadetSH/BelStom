package com.example.belstom.view.cabinet.news.fragment


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
import com.example.belstom.databinding.FragmentNewsBinding
import com.example.belstom.databinding.FragmentStartAuthorizationBinding
import com.example.belstom.room.news.RNews
import com.example.belstom.view.authorization.viewModel.StartAuthorizationViewModel
import com.example.belstom.view.cabinet.news.recyclerNews.NewsAdapter
import com.example.belstom.view.cabinet.news.recyclerNews.NewsItem
import com.example.belstom.view.cabinet.news.viewModel.NewsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentNewsBinding
    private var adapter: NewsAdapter? = null
    private var list = ArrayList<NewsItem>()
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.fragment_title_news)
        )
        initRecycler()
        viewModel.getNews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.readAllNewsLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<List<RNews>> { result ->
                list.clear()
                result.forEach { item ->
                    list.add(
                        NewsItem(
                            item.data,
                            item.title,
                            item.content,
                            item.imagePath
                        )
                    )
                }
                recyclerView?.adapter?.notifyDataSetChanged()
            })
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        adapter = NewsAdapter(
            LayoutInflater.from(requireContext()),
            list
        ) { newsItem: NewsItem, position: Int ->
            (activity as? NewsFragment.OpenNewsDescriptionItem)?.openNewsDescriptionItem(newsItem)
        }
        recyclerView = binding.idRecyclerViewNews
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    interface OpenNewsDescriptionItem{
        fun openNewsDescriptionItem(newsItem: NewsItem)
    }

}