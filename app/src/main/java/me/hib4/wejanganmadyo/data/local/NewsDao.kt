package me.hib4.wejanganmadyo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}