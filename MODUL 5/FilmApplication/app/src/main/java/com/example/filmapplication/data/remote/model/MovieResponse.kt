package com.example.filmapplication.data.remote.model

import com.example.filmapplication.data.local.entity.MovieEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("results")
    val results: List<MovieDto>
)

@Serializable
data class MovieDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("release_date") val releaseDate: String?
)

fun MovieDto.toEntity(category: String): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = "https://image.tmdb.org/t/p/w500${this.posterPath}",
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate,
        category = category
    )
}