package com.org.tmdb.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.org.tmdb.ui.screens.trending.trendingScreen

@Composable
fun TMDBNavHost(
    appState: TMDBAppState,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationRoute.TRENDING_ROUTE.name
) {
    val navHostController = appState.navHostController
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        trendingScreen(onTrendingItemClick = {
            Log.i("Trending",it.name)
        })
    }


}