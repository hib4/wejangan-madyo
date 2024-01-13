package me.hib4.wejanganmadyo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.hib4.wejanganmadyo.domain.usecases.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
) : ViewModel() {
    val news = newsUseCases.getNews(
        sources = listOf("al-jazeera-english", "cnn-indonesia", "google-news")
    ).cachedIn(viewModelScope)
}