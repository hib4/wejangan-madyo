package me.hib4.wejanganmadyo.presentation.details

sealed class DetailEvent {
    data object SaveArticle : DetailEvent()
}