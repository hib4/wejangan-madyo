package me.hib4.wejanganmadyo.domain.usecases.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class SearchNews(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(query = query, sources = sources)
    }
}