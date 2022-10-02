package com.example.herewegoagain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.herewegoagain.databinding.FragmentMovieDetailBinding
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.movie_layout.view.*

class MovieDetailFragment : Fragment() {
    val args: MovieDetailFragmentArgs by navArgs()
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater,
            R.layout.fragment_movie_detail, container, false
        )
        binding.movieTitle.text = args.movieDetails[1]
        Glide.with(binding.movieImage).load(IMAGE_BASE + args.movieDetails[3]).into(binding.movieImage)
        binding.movieOverview.text = args.movieDetails[4]
        return binding.root
    }

}