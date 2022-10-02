package com.example.herewegoagain.updatelist

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.herewegoagain.R
import com.example.herewegoagain.database.Movie
import com.example.herewegoagain.database.MovieViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private lateinit var mMovieViewModel: MovieViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        view.updateButton.setOnClickListener{
            updateItem()

        }
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(){
        val movieName = updateMovieTitle.text.toString()
        val movieGenre = updateMovieGenre.text.toString()
        val movieRating = Integer.parseInt(updateMovieRating.text.toString())

        if(inputCheck(movieName, movieGenre,updateMovieRating.text)){
            val updatedMovie = Movie(args.currentMovie.movieId, movieName, movieGenre, movieRating)

            mMovieViewModel.updateMovie(updatedMovie)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }
    private fun inputCheck(movieName: String, movieGenre: String, movieRating: Editable): Boolean {
        return !(TextUtils.isEmpty(movieName) && TextUtils.isEmpty(movieGenre) && movieRating.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteMovie()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteMovie() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mMovieViewModel.deleteMovie(args.currentMovie)
            Toast.makeText(requireContext(),
                "Successfully removed: ${args.currentMovie.movieName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete ${args.currentMovie.movieName} ?")
        builder.setMessage("Are you sure you want to delete ${args.currentMovie.movieName}")
        builder.create().show()
    }
}