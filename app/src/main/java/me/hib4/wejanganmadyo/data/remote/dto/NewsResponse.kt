package me.hib4.wejanganmadyo.data.remote.dto

import me.hib4.wejanganmadyo.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)