package com.org.tmdb.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.org.tmdb.data.remote.MediaType
import com.org.tmdb.data.remote.TimeWindow
import com.org.tmdb.ui.navigation.TMDBAppState
import com.org.tmdb.ui.navigation.TMDBNavHost
import com.org.tmdb.ui.screens.trending.TrendingScreen
import com.org.tmdb.ui.theme.primaryDarkMode
import com.org.tmdb.ui.theme.primaryVariantDarkMode

@Composable
fun TMDBApp(
    appState: TMDBAppState
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = primaryVariantDarkMode
    ) {
        TMDBNavHost(appState = appState)
    }
}