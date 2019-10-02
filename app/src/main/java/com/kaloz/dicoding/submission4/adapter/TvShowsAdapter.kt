package com.kaloz.dicoding.submission4.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.activity.DetailTvShowActivity
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.ResultTvShow

class TvShowsAdapter(listTvShow: List<ResultTvShow>, context: Context) :
    RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private var mListTvShow = listTvShow
    private val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_shows, parent, false)
        Log.d("Debugging", "Get Inside Adapter")
        itemView.setOnClickListener {
            val intent = Intent(mContext,DetailTvShowActivity::class.java)
            intent.putExtra(Constants.ParcelKey.TvShowToDetail, mListTvShow[parent.indexOfChild(itemView)])
            mContext.startActivity(intent)
        }

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        Log.d("Debugging", "Get Inside Adapter")
        return mListTvShow.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvShowTitle.text = mListTvShow[position].name
        holder.tvShowFirstAirDate.text = mListTvShow[position].firstAirDate
        holder.tvShowPopularity.text = mListTvShow[position].popularity.toString()

        Glide.with(mContext)
            .load(Constants.API.IMAGE_BASE_URL + mListTvShow[position].posterPath)
            .into(holder.tvShowImage)


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvShowImage: ImageView = itemView.findViewById(R.id.tv_image)
        val tvShowTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvShowFirstAirDate: TextView = itemView.findViewById(R.id.tv_first_date)
        val tvShowPopularity: TextView = itemView.findViewById(R.id.tv_popularity)
    }
}