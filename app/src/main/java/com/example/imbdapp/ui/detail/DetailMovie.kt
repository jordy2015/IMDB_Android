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
import com.example.imbdapp.models.Movie
import com.example.imbdapp.ui.home.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailMovie : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val movieSelected = arguments?.getParcelable<Movie>("movieSelected")

        val binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        movieSelected?.let {
            binding.movieSelected = it

            (activity as AppCompatActivity?)?.supportActionBar?.let {nav ->
                nav.setTitle(it.title)
            }
            binding.likeAdd.isChecked = it.isFavorite
            binding.detailRatingBar.rating = it.getRating()
        }

        return binding.root
    }


}
