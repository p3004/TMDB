package com.org.tmdb.ui.screens.details

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.org.tmdb.data.remote.ResultTrending
import com.org.tmdb.ui.navigation.KEY_TRENDING_DATA_CLICKED
import com.org.tmdb.ui.navigation.NavigationRoute
import com.org.tmdb.ui.screens.TrendingSharedViewModel

fun NavHostController.navigateToDetailsScreen(navOptions: NavOptions?= null){
    this.navigate(NavigationRoute.DETAILS_ROUTE.name, navOptions)
}


fun NavGraphBuilder.detailsScreen(trendingSharedViewModel: TrendingSharedViewModel){
    composable(route = NavigationRoute.DETAILS_ROUTE.name){
        DetailsRoute(trendingSharedViewModel)
    }
}