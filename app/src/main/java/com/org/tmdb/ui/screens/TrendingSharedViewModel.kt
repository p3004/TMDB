package com.org.tmdb.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.tmdb.data.remote.ResultTrending
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingSharedViewModel @Inject constructor(): ViewModel(){

    private val _sharedClickedTrendingData = MutableStateFlow<ResultTrending?>(null)

    val sharedClickedTrendingData: StateFlow<ResultTrending?>
        get() = _sharedClickedTrendingData


    fun onTrendingClicked(trending: ResultTrending){
        viewModelScope.launch {
            _sharedClickedTrendingData.value = trending
        }
    }

}