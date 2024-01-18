package me.hib4.wejanganmadyo.domain.usecases.news

import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class GetArticle(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getArticle(url)
    }
}