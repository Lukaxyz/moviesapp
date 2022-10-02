package com.example.herewegoagain.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.herewegoagain.adapters.MovieAdapter
import com.example.herewegoagain.R
import com.example.herewegoagain.models.Movie
import com.example.herewegoagain.models.MovieResponse
import com.example.herewegoagain.services.MovieApiInterface
import com.example.herewegoagain.services.MovieApiService
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        popularRview.layoutManager = LinearLayoutManager(getActivity())
        popularRview.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            popularRview.adapter = MovieAdapter(
                movies,
            ) { selectedItem: Movie ->
                listItemclicked(selectedItem)
            }
        }
    }

    private fun listItemclicked(movie:Movie){
        val myArray = arrayOf(movie.id, movie.title, movie.release,movie.poster,movie.overview)
        val sendData = PopularFragmentDirections.actionPopularFragmentToMovieDetailFragment(myArray)
        view?.let { Navigation.findNavController(it).navigate(sendData) }
    }

    private fun getMovieData(callback: (List<Movie>)->Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }
}