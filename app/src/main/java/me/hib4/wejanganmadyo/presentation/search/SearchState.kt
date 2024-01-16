package me.hib4.wejanganmadyo.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.model.Article

data class SearchState(
    val query: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)
