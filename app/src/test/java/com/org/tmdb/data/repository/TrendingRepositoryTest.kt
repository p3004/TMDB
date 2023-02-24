package com.org.tmdb.data.repository

import com.org.tmdb.data.remote.ApiCalls
import com.org.tmdb.data.remote.trendingTestData
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingRepositoryTest {

    private lateinit var trendingRepository: TrendingRepository

    @Mock
    private lateinit var apiCalls: ApiCalls

    @Before
    fun before(){
        trendingRepository = TrendingRepositoryImpl(apiCalls)
    }

    @Test
    fun `should call getTrendingData from ApiCalls Interface when fetchTrendingData is called`(){
        runBlocking {
            doReturn(trendingTestData)
                .`when`(apiCalls)
                .getTrendingData("", "")

            trendingRepository.fetchTrendingData("","")

            verify(apiCalls).getTrendingData("", "")
        }
    }

}