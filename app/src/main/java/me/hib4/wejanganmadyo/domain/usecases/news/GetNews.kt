package me.hib4.wejanganmadyo.domain.usecases.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class GetNews(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}