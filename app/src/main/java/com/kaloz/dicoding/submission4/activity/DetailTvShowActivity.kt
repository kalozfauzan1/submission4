package com.kaloz.dicoding.submission4.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.database.QueryClass
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.ResultTvShow
import com.kaloz.dicoding.submission4.viewmodel.DetailTvShowViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailTvShowActivity: AppCompatActivity() {

    private var detailTvShowViewModel:DetailTvShowViewModel? = null
    private var isFavourite:Boolean = false
    private var queryClass: QueryClass = QueryClass(this)
    private var tvShows:ResultTvShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.title= "Tv Show"
    }

    override fun onStart() {
        super.onStart()
        detailTvShowViewModel = ViewModelProviders.of(this)[DetailTvShowViewModel::class.java]
        detailTvShowViewModel!!.setTvShow(intent.getParcelableExtra(Constants.ParcelKey.TvShowToDetail))
        detailTvShowViewModel!!.getTvShow().observe(this,tvShow)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)

        isFavourite = detailTvShowViewModel!!.checkFavourite(queryClass,tvShows!!.idTvShow)
        if(isFavourite){
            toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite_red)
        }else{
            toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite_white)
        }
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_favorite) {
            if(isFavourite){
                detailTvShowViewModel?.deleteFavourite(queryClass,tvShows!!)
                item.icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite_white)
                isFavourite = false
            }else{
                detailTvShowViewModel?.setFavourite(queryClass,tvShows!!)
                item.icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite_red)
                isFavourite = true
            }

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private val tvShow:Observer<ResultTvShow> = Observer {
        tvShows = it
        title_movie_detail.text = it.originalName
        popularity_value_detail.text = it.popularity.toString()
        release_title_detail.text = getString(R.string.first_air_date)
        release_value_detail.text = it.firstAirDate
        vote_value_detail.text = it.voteAverage.toString()
        overview_value.text = it.overview

        Glide.with(this)
            .load(Constants.API.IMAGE_BASE_URL + it.backdropPath)
            .into(backdrop)

        Glide.with(this)
            .load(Constants.API.IMAGE_BASE_URL + it.posterPath)
            .into(image_movie)
    }

}