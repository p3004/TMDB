package com.org.tmdb.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.tmdb.data.remote.MediaType
import com.org.tmdb.data.remote.TimeWindow
import com.org.tmdb.ui.theme.TMDBTheme
import com.org.tmdb.ui.theme.primaryVariantDarkMode
import com.org.tmdb.ui.viewmodels.MainViewModel
import com.org.tmdb.ui.viewmodels.TrendingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primaryVariantDarkMode
                ) {
                    TrendingScreen(
                        mediaType = MediaType.TV,
                        timeWindow = TimeWindow.DAY
                    )
                }
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