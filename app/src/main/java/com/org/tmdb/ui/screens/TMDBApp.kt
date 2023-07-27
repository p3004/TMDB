package com.org.tmdb.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.org.tmdb.ui.navigation.TMDBAppState
import com.org.tmdb.ui.navigation.TMDBNavHost
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