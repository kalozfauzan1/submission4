package com.kaloz.dicoding.submission4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaloz.dicoding.submission4.database.QueryClass
import com.kaloz.dicoding.submission4.model.ResultTvShow

class DetailTvShowViewModel : ViewModel() {

    private var tvShowData: MutableLiveData<ResultTvShow> = MutableLiveData()

    fun setTvShow(tvShow: ResultTvShow) {
        tvShowData.postValue(tvShow)
    }

    fun getTvShow(): LiveData<ResultTvShow> {
        return tvShowData
    }

    fun checkFavourite(queryClass: QueryClass, idTvshow:Int):Boolean{
        return queryClass.checkFavouriteTvShow(idTvshow)
    }

    fun setFavourite(queryClass: QueryClass, tvShow: ResultTvShow){
        queryClass.insertTvShowFavourite(tvShow)
    }

    fun deleteFavourite(queryClass: QueryClass, tvShow: ResultTvShow){
        queryClass.deleteTvShow(tvShow.idTvShow)
    }
}