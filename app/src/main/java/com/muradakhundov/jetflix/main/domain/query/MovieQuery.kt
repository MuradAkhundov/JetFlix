package com.muradakhundov.jetflix.main.domain.query

sealed class MovieQuery {

    data class GetMovieQuery(val apiKey : String,val page : Int)

}