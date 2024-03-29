package com.org.tmdb.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Trending(
    val page: Int,
    val results: List<ResultTrending>,
    val total_pages: Int,
    val total_results: Int
)


@Parcelize
data class ResultTrending(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable