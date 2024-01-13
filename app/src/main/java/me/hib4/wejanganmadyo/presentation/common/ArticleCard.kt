package me.hib4.wejanganmadyo.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.hib4.wejanganmadyo.R
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.presentation.Dimens.ArticleCardSize
import me.hib4.wejanganmadyo.presentation.Dimens.ExtraSmallPadding1
import me.hib4.wejanganmadyo.presentation.Dimens.ExtraSmallPadding2

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.clickable { onClick() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(ArticleCardSize)
                .padding(horizontal = ExtraSmallPadding1)
        ) {
            Text(
                text = article.title,
                color = colorResource(id = R.color.text_title),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    color = colorResource(id = R.color.body),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    color = colorResource(id = R.color.body),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}