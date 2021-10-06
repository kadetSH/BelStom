package com.example.belstom.room.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.belstom.room.contactInformation.RContactInformation

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(news: RNews)

    @Query("SELECT * FROM news_table ORDER BY id ASC")
    fun readAllNews(): List<RNews>

    @Query("SELECT * FROM news_table ORDER BY id ASC")
    fun readAllNewsLiveData(): LiveData<List<RNews>>

    @Query("DELETE FROM news_table")
    fun deleteAllNews()

    @Query("SELECT * FROM news_table WHERE newsId = :id1c")
    fun getContact(id1c: String): RNews

    @Query("UPDATE news_table SET title = :title WHERE newsId = :newsId")
    fun updateTitle(newsId : String, title : String)

    @Query("UPDATE news_table SET content = :content WHERE newsId = :newsId")
    fun updateContent(newsId : String, content : String)

    @Query("UPDATE news_table SET imagePath = :imagePath WHERE newsId = :newsId")
    fun updateImagePath(newsId : String, imagePath : String)
}