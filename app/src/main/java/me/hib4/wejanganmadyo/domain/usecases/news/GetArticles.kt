package me.hib4.wejanganmadyo.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class GetArticles(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}