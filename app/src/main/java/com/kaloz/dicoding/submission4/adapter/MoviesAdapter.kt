package com.kaloz.dicoding.submission4.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaloz.dicoding.submission4.activity.DetailMovieActivity
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.ResultMovie
import com.kaloz.dicoding.submission4.R

class MoviesAdapter(
    listMovie: List<ResultMovie>,
    context: Context
) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    private val mContext = context
    private var mListMovie = listMovie

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        itemView.setOnClickListener {
            val intent = Intent(mContext,DetailMovieActivity::class.java)
            intent.putExtra(Constants.ParcelKey.MovieToDetail, mListMovie[parent.indexOfChild(itemView)])
            mContext.startActivity(intent)
        }
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mListMovie.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.movieTitle.text = mListMovie[position].title
        holder.movieReleaseDate.text = mListMovie[position].releaseDate
        holder.moviePopularity.text = mListMovie[position].popularity.toString()

        Glide.with(mContext)
            .load(Constants.API.IMAGE_BASE_URL + mListMovie[position].posterPath)
            .into(holder.movieImage)


    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val moviePopularity: TextView = itemView.findViewById(R.id.movie_popularity)
        val movieReleaseDate: TextView = itemView.findViewById(R.id.movie_release_date)


    }


}