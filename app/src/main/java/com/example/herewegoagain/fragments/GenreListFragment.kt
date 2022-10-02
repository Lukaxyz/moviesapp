package com.example.herewegoagain.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.herewegoagain.adapters.MovieAdapter
import com.example.herewegoagain.R
import com.example.herewegoagain.models.Movie
import com.example.herewegoagain.models.MovieResponse
import com.example.herewegoagain.services.MovieApiInterface
import com.example.herewegoagain.services.MovieApiService
import kotlinx.android.synthetic.main.fragment_genre_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenreListFragment : Fragment() {

        val args:GenreListFragmentArgs by navArgs()
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_genre_list, container, false)
        }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        MoviesByGenresRv.layoutManager = LinearLayoutManager(getActivity())
        MoviesByGenresRv.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            MoviesByGenresRv.adapter = MovieAdapter(movies,null)
        }
    }

    private fun getMovieData(callback: (List<Movie>)->Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieListByGenre(args.genreId).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }


}