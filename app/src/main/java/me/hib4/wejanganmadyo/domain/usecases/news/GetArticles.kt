package me.hib4.wejanganmadyo.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.data.local.NewsDao
import me.hib4.wejanganmadyo.domain.model.Article

class GetArticles(
    private val newsDao: NewsDao,
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}