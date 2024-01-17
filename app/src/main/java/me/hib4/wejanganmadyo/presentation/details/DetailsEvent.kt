package me.hib4.wejanganmadyo.presentation.details

import me.hib4.wejanganmadyo.domain.model.Article

sealed class DetailsEvent {
    data class InsertDeleteArticle(val article: Article) : DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()
}