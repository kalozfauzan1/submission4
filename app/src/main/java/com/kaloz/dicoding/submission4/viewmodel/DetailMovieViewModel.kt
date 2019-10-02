package com.kaloz.dicoding.submission4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaloz.dicoding.submission4.database.QueryClass
import com.kaloz.dicoding.submission4.model.ResultMovie

class DetailMovieViewModel:ViewModel() {

    private var movieData:MutableLiveData<ResultMovie> = MutableLiveData()

    fun setMovieData(movie: ResultMovie){
        movieData.postValue(movie)
    }

    fun getMovieData():LiveData<ResultMovie>{
        return movieData
    }

    fun checkFavourite(queryClass: QueryClass,idMovie:Int):Boolean{
        return queryClass.checkFavouriteMovie(idMovie)
    }

    fun setFavourite(queryClass: QueryClass,movie: ResultMovie){
        queryClass.insertMovieFavourite(movie)
    }

    fun deleteFavourite(queryClass: QueryClass,movie: ResultMovie){
        queryClass.deleteFavouriteMovie(movie.idMovie)
    }




}