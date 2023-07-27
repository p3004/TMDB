package com.org.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.org.tmdb.data.remote.MediaType
import com.org.tmdb.data.remote.TimeWindow
import com.org.tmdb.ui.navigation.TMDBNavHost
import com.org.tmdb.ui.navigation.rememberTMDBAppState
import com.org.tmdb.ui.screens.TMDBApp
import com.org.tmdb.ui.theme.TMDBTheme
import com.org.tmdb.ui.theme.primaryVariantDarkMode
import com.org.tmdb.ui.screens.trending.TrendingScreen
import com.org.tmdb.util.NetworkObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkObserver: NetworkObserver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val userDarkIcons = !isSystemInDarkTheme()
            DisposableEffect(systemUiController, userDarkIcons) {
                systemUiController.systemBarsDarkContentEnabled = !userDarkIcons
                onDispose {}
            }
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                TMDBApp(appState = rememberTMDBAppState(networkObserver = networkObserver))
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TMDBTheme {
        Greeting("Android")
    }
}