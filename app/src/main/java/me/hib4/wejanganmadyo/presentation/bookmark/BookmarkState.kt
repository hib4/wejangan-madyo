package me.hib4.wejanganmadyo.presentation.bookmark

import me.hib4.wejanganmadyo.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)
