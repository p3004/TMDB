package com.org.tmdb.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.tmdb.data.remote.Trending
import com.org.tmdb.data.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository
) : ViewModel() {

    private val _trendingStateFlow =
        MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)

    val trendingStateFlow: StateFlow<MainActivityUiState>
        get() = _trendingStateFlow

    /**
     * stores [MainActivityUiState] into [_trendingStateFlow]
     * */
    fun fetchTrendingData(mediaType: String, timeWindow: String) {
        viewModelScope.launch {
           trendingRepository.fetchTrendingData(
                mediaType, timeWindow
            ).catch {
                _trendingStateFlow.value =
                    MainActivityUiState.Error(it.message ?: "Something went wrong")
            }.collect {
                _trendingStateFlow.value = MainActivityUiState.Success(it)
            }
        }
    }
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val trendingStateFlow: Trending) : MainActivityUiState
    data class Error(val errorMsg: String) : MainActivityUiState
}