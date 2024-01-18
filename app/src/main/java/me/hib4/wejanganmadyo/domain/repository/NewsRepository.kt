package me.hib4.wejanganmadyo.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.model.Article

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun insertArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?

    suspend fun deleteArticle(article: Article)
}