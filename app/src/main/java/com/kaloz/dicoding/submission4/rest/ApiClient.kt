package com.kaloz.dicoding.submission4.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kaloz.dicoding.submission4.helper.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()


        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest= chain.request()
                    ?.newBuilder()?.build()

                chain.proceed(newRequest!!)
            }.build()


        private var retrofit: Retrofit? =null

        fun getClient(): Retrofit?{
            if(retrofit ==null){
                retrofit = Retrofit.Builder().baseUrl(Constants.API.BASE_URL_API + Constants.API.API_VERSION + "/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }
    }
}