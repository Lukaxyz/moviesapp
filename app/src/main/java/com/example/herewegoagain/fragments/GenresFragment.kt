package com.example.herewegoagain.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.herewegoagain.adapters.GenreAdapter
import com.example.herewegoagain.R
import com.example.herewegoagain.models.Genre
import com.example.herewegoagain.models.GenreResponse
import com.example.herewegoagain.services.GenreApiInterface
import com.example.herewegoagain.services.MovieApiService
import kotlinx.android.synthetic.main.fragment_genres.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genres, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        genresRview.layoutManager = LinearLayoutManager(getActivity())
        genresRview.setHasFixedSize(true)
        getGenreData { genres : List<Genre> ->
            genresRview.adapter = GenreAdapter(
                genres,
            ) { selectedItem: Genre ->
                listItemclicked(selectedItem)
            }
        }

    }
    private fun listItemclicked(genre:Genre){
        val sendData = GenresFragmentDirections.actionGenresFragmentToGenreListFragment((genre.id)!!.toInt())
        view?.let { Navigation.findNavController(it).navigate(sendData) }


    }

    private fun getGenreData(callback: (List<Genre>)->Unit){
        val apiService = MovieApiService.getInstance().create(GenreApiInterface::class.java)
        apiService.getGenreList().enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                return callback(response.body()!!.genres)
            }
            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
            }

        })
    }

}