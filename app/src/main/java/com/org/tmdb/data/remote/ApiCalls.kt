package com.org.tmdb.data.remote

import com.org.tmdb.BuildConfig
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

enum class MediaType(name: String){
    ALL("all"),
    MOVIE("moview"),
    TV("tv"),
    PERSON("person")
}

enum class TimeWindow(name: String){
    DAY("day"),
    WEEK("week")
}

interface ApiCalls {

    @GET("/$TRENDING_ENDPOINT/{media_type}/{time_window}")
    suspend fun getTrendingData(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ): Flow<Trending>

}