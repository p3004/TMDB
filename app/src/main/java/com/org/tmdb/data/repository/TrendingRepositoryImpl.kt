package com.org.tmdb.data.repository

import com.org.tmdb.data.remote.ApiCalls
import com.org.tmdb.data.remote.Trending
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val apiCalls: ApiCalls
): TrendingRepository {

    override suspend fun fetchTrendingData(
        mediaType: String,
        timeWindow: String
    ): Flow<Trending> {
        return apiCalls.getTrendingData(
            mediaType = mediaType,
            timeWindow = timeWindow
        )
    }
}