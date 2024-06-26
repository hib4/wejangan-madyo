package me.hib4.wejanganmadyo.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.domain.usecases.news.NewsUseCases
import me.hib4.wejanganmadyo.util.UiComponent
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
) : ViewModel() {
    var sideEffect by mutableStateOf<UiComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.InsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(event.article.url)
                    if (article == null) {
                        insertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun insertArticle(article: Article) {
        newsUseCases.insertArticle(article)
        sideEffect = UiComponent.Toast("Article Saved.")
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        sideEffect = UiComponent.Toast("Article Deleted.")
    }
}