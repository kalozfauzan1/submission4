package com.kaloz.dicoding.submission4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaloz.dicoding.submission4.model.ResultMovie

class FavouriteMovieViewModel :ViewModel(){

    private var movieFavourite: MutableLiveData<MutableList<ResultMovie>> = MutableLiveData()

    fun setListFavourite(listMovie:MutableList<ResultMovie>){
        movieFavourite.postValue(listMovie)
    }
    fun getListFavourite(): LiveData<MutableList<ResultMovie>> {
        return movieFavourite
    }
}