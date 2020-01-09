package com.example.imbdapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.imbdapp.R
import com.example.imbdapp.application.MasterApp
import com.example.imbdapp.models.Movie
import com.example.imbdapp.ui.home.HomeRecyclerAdapter
import javax.inject.Inject

class FavoritesFragment : Fragment(), HomeRecyclerAdapter.MovieItemListener {
    @Inject
    lateinit var viewModel: FavoritesViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeRecyclerAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MasterApp.rootFactory.getHomeComponent().inject(this)

        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        recyclerView = root.findViewById(R.id.favoritesRecyclerView)

        viewModel.moviesData.observe(this, Observer {
            adapter = HomeRecyclerAdapter(requireContext(), this)
            adapter.newMovies(it)
            recyclerView.adapter = adapter
        })
        return root
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            viewModel.refreshData()
        }
    }

    override fun onItemClicked(video: Movie) {
        //to do details
    }
}
