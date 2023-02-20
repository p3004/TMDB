package com.org.tmdb.data.repository

import com.org.tmdb.data.remote.ApiCalls
import com.org.tmdb.data.remote.Trending

class TrendingRepository(
    private val apiCalls: ApiCalls
) {
    suspend fun fetchTrendingData(
        mediaType: String,
        timeWindow: String
    ): Trending {
        return apiCalls.getTrendingData(
            mediaType = mediaType,
            timeWindow = timeWindow
        )
    }
}