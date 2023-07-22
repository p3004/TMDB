package com.org.tmdb.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.org.tmdb.data.remote.ResultTrending
import com.org.tmdb.data.remote.Trending

class TrendingParameterProvider : PreviewParameterProvider<ResultTrending> {
    override val values: Sequence<ResultTrending> = sequenceOf(
        ResultTrending(
            adult = false,
            backdrop_path = "https://image.tmdb.org/t/p/w500/fhquRW28vRZHr26orSaFFnhYIA0.jpg",
            first_air_date = "12/06/2023",
            genre_ids = emptyList(),
            id = 1,
            name = "Extraction-2",
            origin_country = listOf("USA"),
            original_language = "en",
            original_name = "Extraction-2",
            original_title = "Extraction-2",
            overview = "Tasked with extracting a family who is at the mercy of a Georgian gangster, Tyler Rake infiltrates one of the world's deadliest prisons in order to save them. But when the extraction gets hot, and the gangster dies in the heat of battle, his equally ruthless brother tracks down Rake and his team to Vienna, in order to get revenge.",
            popularity = 1234.123,
            poster_path = "https://image.tmdb.org/t/p/w500/fhquRW28vRZHr26orSaFFnhYIA0.jpg",
            release_date = "",
            title = "Extraction-2",
            video = false,
            vote_average = 4.567,
            vote_count = 1
        )
    )
}