package com.example.herewegoagain.services

import com.example.herewegoagain.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("/3/movie/popular?api_key=e99e5077b4d2b39962cd2087b001297f")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/discover/movie?api_key=e99e5077b4d2b39962cd2087b001297f")
    fun getMovieListByGenre(@Query("with_genres") id:Int): Call<MovieResponse>
}
