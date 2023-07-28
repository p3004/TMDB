package com.org.tmdb.ui.screens.trending

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.org.tmdb.BuildConfig
import com.org.tmdb.data.remote.MediaType
import com.org.tmdb.data.remote.ResultTrending
import com.org.tmdb.data.remote.TimeWindow
import com.org.tmdb.ui.common.CommonSpacer
import com.org.tmdb.ui.common.TMDBProgressLoader
import com.org.tmdb.ui.preview.TrendingParameterProvider
import com.org.tmdb.ui.theme.primaryDarkMode
import com.org.tmdb.ui.theme.primaryLightMode
import com.org.tmdb.ui.theme.white
import com.org.tmdb.ui.viewmodels.MainActivityUiState
import com.org.tmdb.ui.viewmodels.MainViewModel


@Composable
fun TrendingScreen(
    mediaType: MediaType,
    timeWindow: TimeWindow,
    viewModel: MainViewModel = hiltViewModel(),
    onTrendingItemClick: (ResultTrending) -> Unit
) {
    viewModel.fetchTrendingData(mediaType.name.lowercase(), timeWindow.name.lowercase())
    TrendingList(
        mainActivityUiState = viewModel.trendingStateFlow.collectAsStateWithLifecycle(),
        onTrendingItemClick
    )

}


@Composable
fun TrendingList(
    mainActivityUiState: State<MainActivityUiState>,
    onTrendingItemClick: (ResultTrending) -> Unit
) {
    when (mainActivityUiState.value) {
        is MainActivityUiState.Loading -> UILoading()
        is MainActivityUiState.Success -> ShowList(
            (mainActivityUiState.value as MainActivityUiState.Success).trendingStateFlow.results,
            onTrendingItemClick
        )

        is MainActivityUiState.Error -> ShowError()
    }
}


@Composable
fun UILoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TMDBProgressLoader()
    }

}


@Composable
fun ShowList(
    trendingList: List<ResultTrending>,
    onTrendingItemClick: (ResultTrending) -> Unit
) {
    LazyColumn {
        items(trendingList) {
            TrendingItem(trending = it, onTrendingItemClick)
        }
    }
}


@Composable
fun TrendingItem(
    trending: ResultTrending,
    onTrendingItemClick: (ResultTrending) -> Unit
) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = primaryDarkMode)
                .clickable { onTrendingItemClick(trending) }

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

@Composable
fun ShowError() {
    Text(text = "Error")
}