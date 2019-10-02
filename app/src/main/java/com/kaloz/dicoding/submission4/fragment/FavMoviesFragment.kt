package com.kaloz.dicoding.submission4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaloz.dicoding.submission4.adapter.MoviesAdapter
import com.kaloz.dicoding.submission4.model.ResultMovie
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.database.QueryClass
import com.kaloz.dicoding.submission4.viewmodel.FavouriteMovieViewModel

class FavMoviesFragment constructor(contentLayoutId: Int) : Fragment(contentLayoutId){

    private var recyclerView: RecyclerView? = null
    private var progressBar: ContentLoadingProgressBar? = null
    private var favMovieViewModel:FavouriteMovieViewModel? = null


    constructor() : this(R.layout.fragment_movies)

    companion object{
        fun createFragment():Fragment{
            return FavMoviesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favMovieViewModel = ViewModelProviders.of(this)[FavouriteMovieViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_movies)
        progressBar = view.findViewById(R.id.progress_movie)

    }

    override fun onStart() {
        super.onStart()
        val queryClass = QueryClass(activity?.applicationContext)
        progressBar?.show()
        favMovieViewModel!!.setListFavourite(queryClass.listFavouriteMovie())
        favMovieViewModel!!.getListFavourite().observe(this,getMovieList)
    }

    private val getMovieList:Observer<List<ResultMovie>> = Observer {
        if(it!=null){
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = MoviesAdapter(it, context!!)
            progressBar?.hide()
        }else{
            Toast.makeText(activity!!.applicationContext,"List Favourite is Empty", Toast.LENGTH_SHORT).show()
        }
    }



}