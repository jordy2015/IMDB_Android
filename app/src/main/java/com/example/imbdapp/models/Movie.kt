package com.example.imbdapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imbdapp.utilities.IMAGES_URL
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey(autoGenerate = true) val _id: Int?,
    @Json(name = "id") val movieId: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    val title: String,
    val overview: String,
    var isFavorite: Boolean = false,
    var watchLater: Boolean = false
): Parcelable {
    constructor(movie: Movie) : this(null, movie.movieId, movie.posterPath, movie.backdropPath, movie.voteAverage, movie.title, movie.overview, movie.isFavorite, movie.watchLater)

    val posterURL get() = "$IMAGES_URL$posterPath"
    //var isFavorite: Boolean = false
    //var watchLater: Boolean = false
}