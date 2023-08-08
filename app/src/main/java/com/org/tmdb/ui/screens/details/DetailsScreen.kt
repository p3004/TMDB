package com.org.tmdb.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.org.tmdb.BuildConfig
import com.org.tmdb.data.remote.ResultTrending
import com.org.tmdb.ui.common.CommonSpacer
import com.org.tmdb.ui.screens.TrendingSharedViewModel
import com.org.tmdb.ui.theme.primaryDarkMode
import com.org.tmdb.ui.theme.white
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsRoute(
    trendingSharedViewModel: TrendingSharedViewModel
) {
    val trending =  trendingSharedViewModel.sharedClickedTrendingData.collectAsStateWithLifecycle()
    trending.value?.let { DetailsScreen(trending = it) }
}


@Composable
fun DetailsScreen(
    trending: ResultTrending
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = primaryDarkMode)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)

        ) {
            CommonSpacer(int = 10)
            AsyncImage(
                model = "${BuildConfig.IMG_BASE_URL}${trending.backdrop_path}",
                contentDescription = "",
            )
            CommonSpacer(int = 10)
            Text(text = trending.overview, color = white)
            CommonSpacer(int = 10)

        }


    }
}


