package com.org.tmdb.ui.screens.trending

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.org.tmdb.data.remote.MediaType
import com.org.tmdb.data.remote.ResultTrending
import com.org.tmdb.data.remote.TimeWindow
import com.org.tmdb.data.remote.Trending
import com.org.tmdb.ui.navigation.NavigationRoute
import com.org.tmdb.ui.screens.TrendingSharedViewModel


fun NavController.navigateToTrending(navOptions: NavOptions? = null) {
    this.navigate(NavigationRoute.TRENDING_ROUTE.name, navOptions)
}

fun NavGraphBuilder.trendingScreen(
    mediaType: MediaType = MediaType.TV,
    timeWindow: TimeWindow = TimeWindow.DAY,
    onTrendingItemClick: () -> Unit,
    trendingSharedViewModel: TrendingSharedViewModel
) {
    composable(route = NavigationRoute.TRENDING_ROUTE.name) {
        TrendingScreen(
            mediaType = mediaType,
            timeWindow = timeWindow,
            onTrendingItemClick = onTrendingItemClick,
            trendingSharedViewModel = trendingSharedViewModel
        )
    }
}