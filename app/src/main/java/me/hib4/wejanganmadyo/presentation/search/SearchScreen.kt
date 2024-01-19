package me.hib4.wejanganmadyo.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.presentation.Dimens.MediumPadding1
import me.hib4.wejanganmadyo.presentation.common.ArticleList
import me.hib4.wejanganmadyo.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.query,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) },
            modifier = Modifier.padding(horizontal = MediumPadding1)
        )
        Spacer(modifier = Modifier.padding(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                articles = articles,
                onClick = navigateToDetails,
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}