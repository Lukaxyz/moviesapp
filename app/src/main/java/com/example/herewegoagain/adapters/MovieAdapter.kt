package com.example.herewegoagain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.herewegoagain.R
import com.example.herewegoagain.models.Movie
import kotlinx.android.synthetic.main.movie_layout.view.*

class MovieAdapter(
    private val movies: List<Movie>,
    private val clickListener: ((Movie) -> Unit)?
)
 : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie, clickListener: ((Movie) -> Unit)?){
            itemView.genreTitle.text = movie.title
            itemView.releaseDate.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.moviePoster)
            itemView.setOnClickListener {
                clickListener?.invoke(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position), clickListener)
    }

    override fun getItemCount(): Int = movies.size
}