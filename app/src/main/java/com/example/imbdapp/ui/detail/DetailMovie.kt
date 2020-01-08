package com.example.imbdapp.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.imbdapp.extensions.getRating
import com.example.imbdapp.databinding.FragmentDetailMovieBinding
import com.example.imbdapp.ui.home.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailMovie : Fragment() {

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(requireActivity()).get(HomeViewModel::class.java)

        val binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        homeViewModel.selectedMovie.observe(this, Observer { movie ->
            (activity as AppCompatActivity?)?.supportActionBar?.let {nav ->
                nav.setTitle(movie.title)
                binding.detailRatingBar.rating = movie.getRating()
            }
        })
        
        return binding.root
    }


}
