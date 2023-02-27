package com.org.tmdb.data.repository

import com.org.tmdb.data.remote.Trending
import kotlinx.coroutines.flow.Flow


interface TrendingRepository {
    suspend fun fetchTrendingData(
        mediaType: String,
        timeWindow: String
    ): Flow<Trending>
}