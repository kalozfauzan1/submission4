package com.kaloz.dicoding.submission4.database

import android.content.Context
import com.kaloz.dicoding.submission4.helper.Constants
import com.kaloz.dicoding.submission4.model.ResultMovie
import com.kaloz.dicoding.submission4.model.ResultTvShow
import org.jetbrains.anko.db.*

class QueryClass(context: Context?) {

    private val mContext = context

    /**
     *
     * SELECT QUERY
     *
     */


    fun checkFavouriteMovie(idMovie: Int): Boolean {
        var resultMovie:ResultMovie? = null
        mContext?.database?.use {
            select(Constants.Table.MovieTable)
                .whereArgs(Constants.MovieColumn.ID + "= {idMovie}","idMovie" to idMovie)
                .exec {
                    resultMovie = parseOpt(object : RowParser<ResultMovie>{
                        override fun parseRow(columns: Array<Any?>): ResultMovie {
                            val data = ResultMovie()
                            data.idMovie = columns[0].toString().toInt()
                            data.voteCount = columns[1].toString().toInt()
                            data.voteAverage = columns[2].toString().toDouble()
                            data.releaseDate = columns[3].toString()
                            data.popularity = columns[4].toString().toDouble()
                            data.title = columns[5].toString()
                            data.backdropPath = columns[6].toString()
                            data.posterPath = columns[7].toString()
                            data.overview = columns[8].toString()
                            return data
                        }
                    })
                }
        }

        return resultMovie!=null
    }

    fun checkFavouriteTvShow(idMovie: Int): Boolean {
        var resultMovie:ResultTvShow? = null
        mContext?.database?.use {
            select(Constants.Table.TvShowsTable)
                .whereArgs(Constants.TvShowsColumn.ID + "= {idTvshow}","idTvshow" to idMovie)
                .exec {
                    resultMovie = parseOpt(object : RowParser<ResultTvShow>{
                        override fun parseRow(columns: Array<Any?>): ResultTvShow {
                            val data = ResultTvShow()
                            data.idTvShow = columns[0].toString().toInt()
                            data.voteCount = columns[1].toString().toInt()
                            data.voteAverage = columns[2].toString().toDouble()
                            data.firstAirDate = columns[3].toString()
                            data.popularity = columns[4].toString().toDouble()
                            data.name = columns[5].toString()
                            data.backdropPath = columns[6].toString()
                            data.posterPath = columns[7].toString()
                            data.overview = columns[8].toString()
                            return data
                        }
                    })
                }
        }

        return resultMovie!=null
    }


    fun listFavouriteMovie(): MutableList<ResultMovie> {
        val listResultMovie: MutableList<ResultMovie> = ArrayList()
        mContext?.database?.use {
            select(Constants.Table.MovieTable)
                .exec {
                    parseList(object : RowParser<ResultMovie> {
                        override fun parseRow(columns: Array<Any?>): ResultMovie {
                            val data = ResultMovie()
                            data.idMovie = columns[0].toString().toInt()
                            data.voteCount = columns[1].toString().toInt()
                            data.voteAverage = columns[2].toString().toDouble()
                            data.releaseDate = columns[3].toString()
                            data.popularity = columns[4].toString().toDouble()
                            data.title = columns[5].toString()
                            data.backdropPath = columns[6].toString()
                            data.posterPath = columns[7].toString()
                            data.overview = columns[8].toString()
                            return data
                        }

                    }).forEach {
                        listResultMovie += it
                    }
                }
        }
        return listResultMovie
    }

    fun listFavouriteTvShows(): MutableList<ResultTvShow> {
        val listResultTvShows: MutableList<ResultTvShow> = ArrayList()
        mContext?.database?.use {
            select(Constants.Table.TvShowsTable)
                .exec {
                    parseList(object : RowParser<ResultTvShow> {
                        override fun parseRow(columns: Array<Any?>): ResultTvShow {
                            val data = ResultTvShow()
                            data.idTvShow = columns[0].toString().toInt()
                            data.voteCount = columns[1].toString().toInt()
                            data.voteAverage = columns[2].toString().toDouble()
                            data.firstAirDate = columns[3].toString()
                            data.popularity = columns[4].toString().toDouble()
                            data.name = columns[5].toString()
                            data.backdropPath = columns[6].toString()
                            data.posterPath = columns[7].toString()
                            data.overview = columns[8].toString()
                            return data
                        }

                    }).forEach {
                        listResultTvShows += it
                    }
                }
        }
        return listResultTvShows
    }

    /**
     *
     *
     * INSERT QUERY
     *
     */

    fun insertMovieFavourite( movie: ResultMovie) {
        mContext?.database?.use {
            insert(
                Constants.Table.MovieTable,
                Constants.MovieColumn.ID to movie.idMovie,
                Constants.MovieColumn.TITLE to movie.title,
                Constants.MovieColumn.POSTER_PATH to movie.posterPath,
                Constants.MovieColumn.BACKDROP_PATH to movie.backdropPath,
                Constants.MovieColumn.POPULARITY to movie.popularity,
                Constants.MovieColumn.RELEASE_DATE to movie.releaseDate,
                Constants.MovieColumn.VOTE_AVERAGE to movie.voteAverage,
                Constants.MovieColumn.VOTE_COUNT to movie.voteCount,
                Constants.MovieColumn.OVERVIEW to movie.overview
            )
        }
    }

    fun insertTvShowFavourite( tvShow: ResultTvShow) {
        mContext?.database?.use {
            insert(
                Constants.Table.TvShowsTable,
                Constants.TvShowsColumn.ID to tvShow.idTvShow,
                Constants.TvShowsColumn.TITLE to tvShow.name,
                Constants.TvShowsColumn.POSTER_PATH to tvShow.posterPath,
                Constants.TvShowsColumn.BACKDROP_PATH to tvShow.backdropPath,
                Constants.TvShowsColumn.POPULARITY to tvShow.popularity,
                Constants.TvShowsColumn.FIRST_AIR_DATE to tvShow.firstAirDate,
                Constants.TvShowsColumn.VOTE_AVERAGE to tvShow.voteAverage,
                Constants.TvShowsColumn.VOTE_COUNT to tvShow.voteCount,
                Constants.TvShowsColumn.OVERVIEW to tvShow.overview
            )
        }
    }

    /**
     *
     *
     * DELETE QUERY
     *
     */


    fun deleteFavouriteMovie(idMovie:Int) {
        mContext?.database?.use {
            delete(Constants.Table.MovieTable,Constants.MovieColumn.ID + "= {idMovie}","idMovie" to idMovie)
        }
    }


    fun deleteTvShow(idTvshow:Int) {
        mContext?.database?.use {
            delete(Constants.Table.TvShowsTable,Constants.TvShowsColumn.ID +"= {idTvshow}","idTvshow" to idTvshow)
        }
    }


}