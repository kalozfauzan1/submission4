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
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.adapter.TvShowsAdapter
import com.kaloz.dicoding.submission4.model.ResultTvShow
import com.kaloz.dicoding.submission4.viewmodel.TvShowViewModel

class TvShowsFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {


    private var recyclerView:RecyclerView? = null
    private var progressBar:ContentLoadingProgressBar? = null
    private var tvShowViewModel:TvShowViewModel? = null
    var list:List<ResultTvShow>? = null

    constructor() : this(R.layout.fragment_tv_shows)

    companion object{
        fun createFragment():Fragment{
            return TvShowsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvShowViewModel = ViewModelProviders.of(activity!!)[TvShowViewModel::class.java]
        Log.d("Debugging", "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_tvshows)
        progressBar = view.findViewById(R.id.progress_tv)
        Log.d("Debugging", "onViewCreated")

    }

    override fun onStart() {
        super.onStart()
        progressBar?.show()
        tvShowViewModel!!.setTvShows()
        tvShowViewModel!!.getListTvShow().observe(this.viewLifecycleOwner,listTvShow)
        tvShowViewModel!!.getError().observe(this.viewLifecycleOwner,errorMessage)

    }

    private val listTvShow:Observer<List<ResultTvShow>> = Observer {
        list = it
        val adapter = TvShowsAdapter(it, context!!)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
        progressBar?.hide()
    }

    private val errorMessage:Observer<String> = Observer {
        progressBar?.hide()
        Toast.makeText(context,"Failed to get Data", Toast.LENGTH_SHORT).show()
    }


}