package com.org.tmdb.data.ui

import com.org.tmdb.data.remote.errorMessage
import com.org.tmdb.data.remote.trendingTestData
import com.org.tmdb.data.repository.TrendingRepository
import com.org.tmdb.data.util.TestDispatcherRule
import com.org.tmdb.ui.screens.trending.MainActivityUiState
import com.org.tmdb.ui.screens.trending.TrendingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals


@RunWith(MockitoJUnitRunner::class)
class TrendingViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()


    @Mock
    private lateinit var trendingRepository: TrendingRepository

    private lateinit var trendingViewModel: TrendingViewModel


    @Before
    fun setUp(){
        trendingViewModel = TrendingViewModel(trendingRepository)
    }


    @Test
    fun stateIsInitialLoading(){
        assertEquals(MainActivityUiState.Loading, trendingViewModel.trendingStateFlow.value)
    }


    @Test
    fun `when server response 200 should fetch trending data`(){
        runBlocking {
            doReturn(trendingTestData)
                .`when`(trendingRepository)
                .fetchTrendingData("","")

            trendingViewModel.fetchTrendingData("", "")

            verify(trendingRepository).fetchTrendingData("","")
        }
    }


    @Test
    fun `when server response ERROR should return error message`(){
        runBlocking {
         doThrow(RuntimeException(errorMessage))
             .`when`(trendingRepository)
             .fetchTrendingData("","")

         trendingViewModel.fetchTrendingData("","")

         verify(trendingRepository).fetchTrendingData("","").catch {
             assertEquals(it.message, errorMessage)
         }
        }
    }

}