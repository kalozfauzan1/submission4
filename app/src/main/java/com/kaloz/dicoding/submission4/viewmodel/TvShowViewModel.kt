package com.kaloz.dicoding.submission4.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.ResultTvShow
import com.kaloz.dicoding.submission4.model.TvShow
import com.kaloz.dicoding.submission4.rest.ApiClient
import com.kaloz.dicoding.submission4.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {

    private var listTvShow: MutableLiveData<List<ResultTvShow>> = MutableLiveData()
    private var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun setTvShows() {
        val apiClient = ApiClient.getClient()?.create(ApiInterface::class.java)
        val call = apiClient?.getTvShowList(Constants.API.API_KEY, "popularity.desc")
        call?.enqueue(object : Callback<TvShow> {
            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                t.printStackTrace()
                errorMessage.postValue(t.toString())
            }

            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                listTvShow.postValue(response.body()?.results)

            }

        })
    }

    fun getListTvShow():LiveData<List<ResultTvShow>>{
        return listTvShow
    }

    fun getError():LiveData<String>{
        return errorMessage
    }

}