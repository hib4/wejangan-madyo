package me.hib4.wejanganmadyo.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import me.hib4.wejanganmadyo.R
import me.hib4.wejanganmadyo.presentation.Dimens.ExtraSmallPadding1
import me.hib4.wejanganmadyo.presentation.Dimens.MediumPadding1
import me.hib4.wejanganmadyo.presentation.Dimens.MediumPadding2
import me.hib4.wejanganmadyo.presentation.onboarding.Page
import me.hib4.wejanganmadyo.presentation.onboarding.pages
import me.hib4.wejanganmadyo.ui.theme.WejanganMadyoTheme

@Composable
fun OnBoardingCard(
    page: Page,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.title,
            color = colorResource(id = R.color.display_small),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = MediumPadding2)
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding1))
        Text(
            text = page.description,
            color = colorResource(id = R.color.text_medium),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MediumPadding2)
        )
    }
}

@Preview()
@Composable
fun OnBoardingCardPreview() {
    WejanganMadyoTheme {
        OnBoardingCard(
            page = pages[0]
        )
    }
}