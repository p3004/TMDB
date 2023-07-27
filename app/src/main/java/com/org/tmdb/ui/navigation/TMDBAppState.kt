package com.org.tmdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.org.tmdb.util.NetworkObserver
import com.org.tmdb.util.NetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberTMDBAppState(
    networkObserver: NetworkObserver,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navHostController: NavHostController = rememberNavController()
): TMDBAppState{
    return remember(
        navHostController,
        coroutineScope,
        networkObserver
    ) {
        TMDBAppState(
            coroutineScope = coroutineScope,
            networkObserver = networkObserver,
            navHostController = navHostController
        )
    }
}



class TMDBAppState(
    val coroutineScope: CoroutineScope,
    val networkObserver: NetworkObserver,
    val navHostController: NavHostController
) {


    val isOffline = networkObserver.observe()
        .map {
            when (it) {
                NetworkStatus.CONNECTED -> true
                NetworkStatus.UNAVAILABLE,
                NetworkStatus.DISCONNECTED,
                NetworkStatus.DISCONNECTING -> false
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}