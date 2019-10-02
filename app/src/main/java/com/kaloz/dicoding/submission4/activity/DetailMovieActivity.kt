package com.kaloz.dicoding.submission4.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.ResultMovie
import com.kaloz.dicoding.submission4.viewmodel.DetailMovieViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import androidx.core.content.ContextCompat
import com.kaloz.dicoding.submission4.database.QueryClass


class DetailMovieActivity : AppCompatActivity() {

    private var movieViewModel:DetailMovieViewModel? = null
    private var movie:ResultMovie? = null
    private var isFavourite:Boolean = false
    private var queryClass:QueryClass = QueryClass(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.title= "Movie"
    }

    override fun onStart() {
        super.onStart()
        movieViewModel = ViewModelProviders.of(this)[DetailMovieViewModel::class.java]
        movieViewModel!!.setMovieData(intent.getParcelableExtra(Constants.ParcelKey.MovieToDetail))
        movieViewModel!!.getMovieData().observe(this, movieData)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)

        isFavourite = movieViewModel!!.checkFavourite(queryClass,movie!!.idMovie)
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
                movieViewModel?.deleteFavourite(queryClass,movie!!)
                item.icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite_white)
                isFavourite = false
            }else{
                movieViewModel?.setFavourite(queryClass,movie!!)
                item.icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite_red)
                isFavourite = true
            }

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private val movieData:Observer<ResultMovie> = Observer {
        movie=it
        title_movie_detail.text = it.title
        popularity_value_detail.text = it.popularity.toString()
        release_value_detail.text = it.releaseDate
        release_title_detail.text = getString(R.string.release)
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