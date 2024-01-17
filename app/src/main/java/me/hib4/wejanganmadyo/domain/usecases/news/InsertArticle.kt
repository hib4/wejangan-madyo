package me.hib4.wejanganmadyo.domain.usecases.news

import me.hib4.wejanganmadyo.data.local.NewsDao
import me.hib4.wejanganmadyo.domain.model.Article

class InsertArticle(
    private val newsDao: NewsDao,
) {
    suspend operator fun invoke(article: Article) {
        newsDao.insert(article)
    }
}