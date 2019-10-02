package com.kaloz.dicoding.submission4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaloz.dicoding.submission4.model.ResultTvShow

class FavouriteTvShowViewModel :ViewModel(){

    private var movieFavourite: MutableLiveData<MutableList<ResultTvShow>> = MutableLiveData()
    private var noDataMessage:MutableLiveData<String> = MutableLiveData()

    fun setListFavourite(listMovie:MutableList<ResultTvShow>){
        movieFavourite.postValue(listMovie)
    }
    fun getListFavourite(): LiveData<MutableList<ResultTvShow>> {
        return movieFavourite
    }

    fun setNoDataMessage(message:String){
        noDataMessage.postValue(message)
    }

    fun getNoDataMessage():LiveData<String>{
        return noDataMessage
    }
}