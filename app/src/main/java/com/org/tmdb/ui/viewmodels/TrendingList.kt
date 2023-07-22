package com.org.tmdb.ui.viewmodels

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.org.tmdb.BuildConfig
import com.org.tmdb.data.remote.MediaType
import com.org.tmdb.data.remote.ResultTrending
import com.org.tmdb.data.remote.TimeWindow
import com.org.tmdb.data.remote.Trending
import com.org.tmdb.ui.common.CommonSpacer
import com.org.tmdb.ui.preview.TrendingParameterProvider
import com.org.tmdb.ui.theme.primaryDarkMode
import com.org.tmdb.ui.theme.primaryLightMode
import com.org.tmdb.ui.theme.white


@Composable
fun TrendingScreen(
    mediaType: MediaType,
    timeWindow: TimeWindow,
    viewModel: MainViewModel = hiltViewModel()
) {
    viewModel.fetchTrendingData(mediaType.name.lowercase(), timeWindow.name.lowercase())
    TrendingList(mainActivityUiState = viewModel.trendingStateFlow.collectAsStateWithLifecycle())

}


@Composable
fun TrendingList(
    mainActivityUiState: State<MainActivityUiState>
) {
    when (mainActivityUiState.value) {
        is MainActivityUiState.Loading -> UILoading()
        is MainActivityUiState.Success -> ShowList((mainActivityUiState.value as MainActivityUiState.Success).trendingStateFlow.results)
        is MainActivityUiState.Error -> ShowError()
    }
}


@Composable
fun UILoading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .progressSemantics()
                .size(32.dp),
            color = if (isSystemInDarkTheme()) primaryDarkMode else primaryLightMode,
            strokeWidth = 8.dp
        )
    }

}


@Composable
fun ShowList(trendingList: List<ResultTrending>) {
    LazyColumn {
        items(trendingList) {
            TrendingItem(trending = it)
        }
    }
}


@Composable
fun TrendingItem(trending: ResultTrending) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = primaryDarkMode)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp)
                    .padding(10.dp)
                ) {
                CommonSpacer(int = 10)
                AsyncImage(
                    model = "${BuildConfig.IMG_BASE_URL}${trending.backdrop_path}",
                    contentDescription = "",
                )
                CommonSpacer(int = 10)
                Text(text = trending.name, color = white)
                CommonSpacer(int = 10)
            }
        }
        CommonSpacer(int = 40)
    }

}


@Preview
@Composable
fun TrendingItemPreview(@PreviewParameter(TrendingParameterProvider::class) resultTrending: ResultTrending) {
    TrendingItem(trending = resultTrending)
}

@Composable
fun ShowError() {
    Text(text = "Error")
}