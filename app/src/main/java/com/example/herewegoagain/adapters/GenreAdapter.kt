package com.example.herewegoagain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.herewegoagain.R
import com.example.herewegoagain.models.Genre
import kotlinx.android.synthetic.main.movie_layout.view.*

class GenreAdapter(
    private val genres: List<Genre>,
    private val clickListener:(Genre)->Unit
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindGenre(genre: Genre, clickListener:(Genre)->Unit){
            itemView.genreTitle.text = genre.name

            itemView.setOnClickListener {
                clickListener(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genre_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindGenre(genres.get(position), clickListener)
    }

    override fun getItemCount(): Int = genres.size
}