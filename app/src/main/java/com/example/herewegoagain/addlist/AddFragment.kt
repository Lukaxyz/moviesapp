package com.example.herewegoagain.addlist

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.herewegoagain.R
import com.example.herewegoagain.database.Movie
import com.example.herewegoagain.database.MovieViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mMovieViewModel: MovieViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        view.addButton.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val movieName = addMovieTitle.text.toString()
        val movieGenre = addMovieGenre.text.toString()
        val movieRating = addMovieRating.text

        if(inputCheck(movieName, movieGenre, movieRating)){
            val movie = Movie(0,movieName, movieGenre, Integer.parseInt(movieRating.toString()))
            mMovieViewModel.addMovie(movie)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }


    }

    private fun inputCheck(movieName: String, movieGenre: String, movieRating: Editable): Boolean {
        return !(TextUtils.isEmpty(movieName) && TextUtils.isEmpty(movieGenre) && movieRating.isEmpty())
    }

}