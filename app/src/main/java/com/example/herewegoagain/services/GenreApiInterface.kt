package com.example.herewegoagain.services

import com.example.herewegoagain.models.GenreResponse
import retrofit2.Call
import retrofit2.http.GET

interface GenreApiInterface {
    @GET("/3/genre/movie/list?api_key=e99e5077b4d2b39962cd2087b001297f")
    fun getGenreList(): Call<GenreResponse>
}