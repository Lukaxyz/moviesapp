package com.example.herewegoagain

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.herewegoagain.adapters.ListAdapter
import com.example.herewegoagain.database.MovieViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mMovieViewModel : MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerviewlist
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        //MovieViewModel
        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        mMovieViewModel.readAllData.observe(viewLifecycleOwner, Observer{ movie -> adapter.setData(movie)

        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllMovies()
        }


        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllMovies() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mMovieViewModel.deleteAllMovies()
            Toast.makeText(requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete everything ?")
        builder.setMessage("Are you sure you want to delete everything? ")
        builder.create().show()
    }
}