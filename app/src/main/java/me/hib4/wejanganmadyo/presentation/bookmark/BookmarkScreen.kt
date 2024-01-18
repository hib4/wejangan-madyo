package me.hib4.wejanganmadyo.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import me.hib4.wejanganmadyo.R
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.presentation.Dimens.MediumPadding1
import me.hib4.wejanganmadyo.presentation.common.ArticleList

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = MediumPadding1, top = MediumPadding1, end = MediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            color = colorResource(id = R.color.text_title),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticleList(
            articles = state.articles,
            onClick = { navigateToDetails(it) }
        )
    }
}