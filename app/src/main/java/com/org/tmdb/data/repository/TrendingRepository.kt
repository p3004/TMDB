package com.org.tmdb.data.repository

import com.org.tmdb.data.remote.Trending

interface TrendingRepository {
    suspend fun fetchTrendingData(
        mediaType: String,
        timeWindow: String
    ): Trending
}