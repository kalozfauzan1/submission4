package com.kaloz.dicoding.submission4.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("page")
    @Expose
    var page: Int = 0,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int = 0,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0,
    @SerializedName("results")
    @Expose
    var results: List<ResultMovie>

) : Parcelable

@Parcelize
data class ResultMovie(
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0,
    @SerializedName("id")
    @Expose
    var idMovie: Int = 0,
    @SerializedName("video")
    @Expose
    var video: Boolean = false,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.0,
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String = "",
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String = "",
    @SerializedName("original_title")
    @Expose
    var originalTitle: String = "",
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String = "",
    @SerializedName("adult")
    @Expose
    var adult: Boolean = false,
    @SerializedName("overview")
    @Expose
    var overview: String = "",
    @SerializedName("release_date")
    @Expose
    var releaseDate: String = ""
):Parcelable





