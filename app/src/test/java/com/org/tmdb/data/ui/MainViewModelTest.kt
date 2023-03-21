package com.org.tmdb.data.ui

import com.org.tmdb.data.repository.TrendingRepository
import com.org.tmdb.data.util.TestDispatcherRule
import com.org.tmdb.ui.viewmodels.MainActivityUiState
import com.org.tmdb.ui.viewmodels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()


    @Mock
    private lateinit var trendingRepository: TrendingRepository

    private lateinit var mainViewModel: MainViewModel


    @Before
    fun setUp(){
        mainViewModel = MainViewModel(trendingRepository)
    }


    @Test
    fun stateIsInitialLoading(){
        assertEquals(MainActivityUiState.Loading, mainViewModel.trendingStateFlow.value)
    }


    @Test
    fun `when server response 200 should fetch trending data`(){
        
    }


    @Test
    fun `when server`(){

    }

}