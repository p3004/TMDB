package com.org.tmdb.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.org.tmdb.ui.screens.TrendingSharedViewModel
import com.org.tmdb.ui.screens.details.detailsScreen
import com.org.tmdb.ui.screens.details.navigateToDetailsScreen
import com.org.tmdb.ui.screens.trending.trendingScreen

const val KEY_TRENDING_DATA_CLICKED = "trending_data_clicked"

@Composable
fun TMDBNavHost(
    appState: TMDBAppState,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationRoute.TRENDING_ROUTE.name,
    trendingSharedViewModel: TrendingSharedViewModel = hiltViewModel(),
) {
    val navHostController = appState.navHostController
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        trendingScreen(onTrendingItemClick = {
            navHostController.navigateToDetailsScreen()
        },
            trendingSharedViewModel = trendingSharedViewModel)
        detailsScreen(trendingSharedViewModel)
    }


}