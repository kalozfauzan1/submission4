package com.kaloz.dicoding.submission4.fragment

import android.os.Bundle
import android.util.Log
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
import com.kaloz.dicoding.submission4.viewmodel.MovieViewModel

class MoviesFragment constructor(contentLayoutId: Int) : Fragment(contentLayoutId){

    private var recyclerView: RecyclerView? = null
    private var progressBar: ContentLoadingProgressBar? = null
    private var movieViewModel:MovieViewModel? = null

    constructor() : this(R.layout.fragment_movies)

    companion object{
        fun createFragment():Fragment{
            return MoviesFragment()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieViewModel = ViewModelProviders.of(this)[MovieViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_movies)
        progressBar = view.findViewById(R.id.progress_movie)

    }

    override fun onStart() {
        super.onStart()
        progressBar?.show()
        movieViewModel!!.setMovie()
        movieViewModel!!.getMovie().observe(this,getMovieList)
        movieViewModel!!.getErrorMessage().observe(this,getErrorMessage)
    }

    private val getMovieList:Observer<List<ResultMovie>> = Observer {
        if(it!=null){
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = MoviesAdapter(it, context!!)
            progressBar?.hide()
        }
    }

    private val getErrorMessage:Observer<String> = Observer {
        if(it!=null){
            progressBar?.hide()
            Toast.makeText(context,"Failed to get Data",Toast.LENGTH_SHORT).show()
            Log.d("ERROR MOVIES :",it)
        }
    }

}