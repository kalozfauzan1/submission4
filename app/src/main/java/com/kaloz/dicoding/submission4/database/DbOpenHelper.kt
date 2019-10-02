package com.kaloz.dicoding.submission4.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kaloz.dicoding.submission4.helper.Constants
import org.jetbrains.anko.db.*

class DbOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "Movie.db", null, 1) {

    companion object {
        private var instance: DbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DbOpenHelper {
            if (instance == null) {
                instance = DbOpenHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(
            Constants.Table.MovieTable, true,
            Constants.MovieColumn.ID to INTEGER + PRIMARY_KEY,
            Constants.MovieColumn.VOTE_COUNT to INTEGER,
            Constants.MovieColumn.VOTE_AVERAGE to REAL,
            Constants.MovieColumn.RELEASE_DATE to TEXT,
            Constants.MovieColumn.POPULARITY to REAL,
            Constants.MovieColumn.TITLE to TEXT,
            Constants.MovieColumn.BACKDROP_PATH to TEXT,
            Constants.MovieColumn.POSTER_PATH to REAL,
            Constants.MovieColumn.OVERVIEW to TEXT
        )

        p0?.createTable(
            Constants.Table.TvShowsTable, true,
            Constants.MovieColumn.ID to INTEGER + PRIMARY_KEY,
            Constants.TvShowsColumn.VOTE_COUNT to INTEGER,
            Constants.TvShowsColumn.VOTE_AVERAGE to REAL,
            Constants.TvShowsColumn.FIRST_AIR_DATE to TEXT,
            Constants.TvShowsColumn.POPULARITY to REAL,
            Constants.TvShowsColumn.TITLE to TEXT,
            Constants.TvShowsColumn.BACKDROP_PATH to TEXT,
            Constants.TvShowsColumn.POSTER_PATH to REAL,
            Constants.TvShowsColumn.OVERVIEW to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}

    val Context.database: DbOpenHelper
    get() = DbOpenHelper.getInstance(applicationContext)
