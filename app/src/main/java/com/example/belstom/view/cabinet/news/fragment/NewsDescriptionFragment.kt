package com.example.belstom.view.cabinet.news.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.belstom.Constants
import com.example.belstom.R
import com.example.belstom.TitleController
import com.example.belstom.databinding.FragmentNewsDescriptionBinding
import com.example.belstom.view.cabinet.news.recyclerNews.NewsItem
import com.example.belstom.view.cabinet.news.viewModel.NewsDescriptionViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsDescriptionFragment: DaggerFragment() {


    companion object {
        fun newInstance(newsItem: NewsItem?): NewsDescriptionFragment {
            val args = Bundle()
            args.putSerializable(Constants.NEWS_ITEM, newsItem)
            val fragment = NewsDescriptionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsDescriptionViewModel by viewModels {
        viewModelFactory
    }

    var newsItem: NewsItem? = null
    private lateinit var binding: FragmentNewsDescriptionBinding
    var btm: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as? TitleController)?.setTitle(
            requireContext().resources.getString(R.string.drawer_menu_news_description)
        )
        arguments?.let { arg ->
            newsItem = arg.getSerializable(Constants.NEWS_ITEM) as NewsItem?
        }
        init()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.viewPhoto.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<Bitmap> { result ->
                btm = result
                btm?.let {
                    val imageNews = binding.appBarImage
//                    imageNews.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageNews.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    imageNews.setImageBitmap(btm)
                }

            })
    }

    private fun init() {
        newsItem?.let { item ->
            val collaps = binding.idCollapsingToolbar
            collaps.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

            val titleNews = binding.toolbarM
            val descriptionNews = binding.textDescriptionNews
            titleNews.title = item.title
            descriptionNews.text = item.content
            if (item.imagePath != "") {
                loadImage(item.imagePath)
            }else{
                val imageNews = binding.appBarImage
                imageNews.visibility = View.INVISIBLE

            }
        }
    }

    private fun loadImage(imagePath: String){
        viewModel.loadImage(imagePath)
    }

}