package com.kaloz.dicoding.submission4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.Movie
import com.kaloz.dicoding.submission4.model.ResultMovie
import com.kaloz.dicoding.submission4.rest.ApiClient
import com.kaloz.dicoding.submission4.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private var listMovie: MutableLiveData<List<ResultMovie>> = MutableLiveData()
    private var errorMesage: MutableLiveData<String> = MutableLiveData()


    fun setMovie() {
        val apiInterface: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
        val call: Call<Movie> = apiInterface.getMovieList(Constants.API.API_KEY, "popularity.desc")
        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
                errorMesage.postValue(t.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                listMovie.postValue(response.body()?.results)

            }

        })
    }

    fun getMovie():LiveData<List<ResultMovie>>{
        return listMovie
    }

    fun getErrorMessage():LiveData<String>{
        return errorMesage
    }

}