package com.kaloz.dicoding.submission4.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultTvShow(
    @SerializedName("original_name")
    @Expose
    var originalName: String = "",
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null,
    @SerializedName("name")
    @Expose
    var name: String = "",
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.0,
    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0,
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String = "",
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String = "",
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String = "",
    @SerializedName("id")
    @Expose
    var idTvShow: Int = 0,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.0,
    @SerializedName("overview")
    @Expose
    var overview: String = "",
    @SerializedName("poster_path")
    @Expose
    var posterPath: String = ""
):Parcelable

@Parcelize
data class TvShow(
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,
    @SerializedName("results")
    @Expose
    var results: List<ResultTvShow>? = null
):Parcelable