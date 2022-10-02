package com.example.herewegoagain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.herewegoagain.ListFragmentDirections
import com.example.herewegoagain.R
import com.example.herewegoagain.database.Movie
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var movieList = emptyList<Movie>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate (R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.itemView.movieId_txt.text = currentItem.movieId.toString()
        holder.itemView.movieTitle_txt.text = currentItem.movieName
        holder.itemView.movieGenre_txt.text = currentItem.movieGenre
        holder.itemView.movieRating_txt.text = currentItem.movieRating.toString()

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(movie: List<Movie>){
        this.movieList = movie
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}