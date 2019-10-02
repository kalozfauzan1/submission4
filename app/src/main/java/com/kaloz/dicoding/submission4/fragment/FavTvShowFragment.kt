package com.kaloz.dicoding.submission4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.adapter.TvShowsAdapter
import com.kaloz.dicoding.submission4.database.QueryClass
import com.kaloz.dicoding.submission4.model.ResultTvShow
import com.kaloz.dicoding.submission4.viewmodel.FavouriteTvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_shows.*

class FavTvShowFragment constructor(contentLayoutId: Int) : Fragment(contentLayoutId){

    private var recyclerView: RecyclerView? = null
    private var progressBar: ContentLoadingProgressBar? = null
    private var favTvShowViewModel:FavouriteTvShowViewModel? = null


    constructor() : this(R.layout.fragment_movies)

    companion object{
        fun createFragment():Fragment{
            return FavTvShowFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favTvShowViewModel = ViewModelProviders.of(this)[FavouriteTvShowViewModel::class.java]
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
        favTvShowViewModel!!.setListFavourite(queryClass.listFavouriteTvShows())
        favTvShowViewModel!!.setNoDataMessage(getString(R.string.empty_data))
        favTvShowViewModel!!.getListFavourite().observe(this,getTvShowList)
    }

    private val getTvShowList:Observer<List<ResultTvShow>> = Observer {
        if(it?.size==0  ){
            progressBar?.hide()
            favTvShowViewModel?.getNoDataMessage()?.observe(this,noDataMessage)
        }else{
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = TvShowsAdapter(it, context!!)
            progressBar?.hide()

        }
    }

    private val noDataMessage:Observer<String> = Observer {
        error_label.text = it
        error_label.visibility = View.VISIBLE
    }



}