package com.kaloz.dicoding.submission4.rest

import com.kaloz.dicoding.submission4.model.Movie
import com.kaloz.dicoding.submission4.model.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("discover/movie")
    fun getMovieList(@Query("api_key") apiKey:String,
                     @Query("sort_by") sortBy:String):Call<Movie>

    @GET("discover/tv")
    fun getTvShowList(@Query("api_key") apiKey:String,
                     @Query("sort_by") sortBy:String):Call<TvShow>

}