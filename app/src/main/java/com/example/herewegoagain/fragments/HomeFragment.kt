package com.example.herewegoagain.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.herewegoagain.R
import com.example.herewegoagain.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )
        binding.buttonPopular.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_popularFragment)
        }
        binding.buttonGenres.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_genresFragment)
        }
        binding.buttonWatchList.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shareFragment -> {
                shareMessage()
                true
            }

            else ->  return NavigationUI.
               onNavDestinationSelected(item,requireView().findNavController())
                      || super.onOptionsItemSelected(item)
        }
    }
    private fun getShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, "Check out this app called My Movies!, it's a movie catalogue with the most up to date movies")
        return shareIntent
    }
    private fun shareMessage(){
        startActivity(getShareIntent())
    }
}
