package com.example.belstom.view.cabinet.news.recyclerNews

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belstom.R

class NewsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val data: TextView = itemView.findViewById<TextView>(R.id.fr_template_news_data)
    private val title: TextView = itemView.findViewById<TextView>(R.id.fr_template_news_title)
    private val content: TextView = itemView.findViewById<TextView>(R.id.fr_template_news_content)
    val btn: Button = itemView.findViewById<Button>(R.id.fr_template_news_btn)


    fun bind(item: NewsItem){
        data.text = item.data
        title.text = item.title
        content.text = item.content
    }

}