package me.hib4.wejanganmadyo.domain.usecases.news

import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}