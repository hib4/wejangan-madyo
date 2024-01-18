package me.hib4.wejanganmadyo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import me.hib4.wejanganmadyo.data.local.NewsDao
import me.hib4.wejanganmadyo.data.remote.NewsApi
import me.hib4.wejanganmadyo.data.remote.NewsPagingSource
import me.hib4.wejanganmadyo.data.remote.SearchNewsPagingSource
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10)
        ) {
            NewsPagingSource(
                newsApi = newsApi,
                sources = sources.joinToString(separator = ",")
            )
        }.flow
    }

    override fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10)
        ) {
            SearchNewsPagingSource(
                newsApi = newsApi,
                query = query,
                sources = sources.joinToString(separator = ",")
            )
        }.flow
    }

    override suspend fun insertArticle(article: Article) {
        newsDao.insert(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }
}