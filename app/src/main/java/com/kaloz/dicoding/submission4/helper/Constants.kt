package com.kaloz.dicoding.submission4.helper

class Constants {

    class API {
        companion object {
            const val BASE_URL_API = "https://api.themoviedb.org/"
            const val API_KEY = "e6c934c74aa5ea4d7af1b160c4e543ce"
            const val API_VERSION = "3"
            const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        }
    }

    class ParcelKey {
        companion object {
            const val MovieToDetail = "1"
            const val TvShowToDetail = "1"
        }
    }

    class Table {


        companion object {
            const val MovieTable = "CATEGORY"
            const val TvShowsTable = "CONTENT"
        }
    }

    class TvShowsColumn{
        companion object {
            const val ID = "id_movie"
            const val TITLE = "title"
            const val VOTE_COUNT = "vote_count"
            const val VOTE_AVERAGE = "vote_average"
            const val POPULARITY = "popularity"
            const val POSTER_PATH = "poster_path"
            const val BACKDROP_PATH = "backdrop_path"
            const val FIRST_AIR_DATE = "first_air_date"
            const val OVERVIEW = "overview"
        }
    }

    class MovieColumn{
        companion object {
            const val ID = "id_movie"
            const val TITLE = "title"
            const val VOTE_COUNT = "vote_count"
            const val VOTE_AVERAGE = "vote_average"
            const val POPULARITY = "popularity"
            const val POSTER_PATH = "poster_path"
            const val BACKDROP_PATH = "backdrop_path"
            const val RELEASE_DATE = "release_date"
            const val OVERVIEW = "overview"
        }
    }


}