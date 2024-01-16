package me.hib4.wejanganmadyo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.data.remote.NewsApi
import me.hib4.wejanganmadyo.data.remote.NewsPagingSource
import me.hib4.wejanganmadyo.data.remote.SearchNewsPagingSource
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
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
}