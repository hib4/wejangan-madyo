package me.hib4.wejanganmadyo.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val query: String) : SearchEvent()

    data object SearchNews : SearchEvent()
}