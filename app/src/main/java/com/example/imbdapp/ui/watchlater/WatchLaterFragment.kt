package com.example.imbdapp.ui.watchlater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.imbdapp.R
import com.example.imbdapp.application.MasterApp
import com.example.imbdapp.models.Movie
import com.example.imbdapp.ui.home.HomeRecyclerAdapter
import javax.inject.Inject

class WatchLaterFragment : Fragment(), HomeRecyclerAdapter.MovieItemListener {
    @Inject
    lateinit var galleryViewModel: WatchLaterViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeRecyclerAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MasterApp.rootFactory.getHomeComponent().inject(this)

        val root = inflater.inflate(R.layout.fragment_watch_later, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        recyclerView = root.findViewById(R.id.watchLaterRecyclerView)

        galleryViewModel.moviesData.observe(this, Observer {
            adapter = HomeRecyclerAdapter(requireContext(), this)
            adapter.newMovies(it)
            recyclerView.adapter = adapter
        })
        return root
    }

    override fun onItemClicked(video: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movieSelected", video)
        navController.navigate(R.id.action_watch_later_to_detailMovie, bundle)
    }
}