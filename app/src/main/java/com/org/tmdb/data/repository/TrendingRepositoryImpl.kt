package com.org.tmdb.data.repository

import com.org.tmdb.data.remote.ApiCalls
import com.org.tmdb.data.remote.Trending
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val apiCalls: ApiCalls
) : TrendingRepository {

    override suspend fun fetchTrendingData(
        mediaType: String,
        timeWindow: String
    ): Flow<Trending> {
        return withContext(Dispatchers.IO) {
            apiCalls.getTrendingData(
                mediaType = mediaType,
                timeWindow = timeWindow
            )
        }
    }
}