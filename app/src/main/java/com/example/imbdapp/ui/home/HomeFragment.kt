package com.example.imbdapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.imbdapp.models.Movie
import com.example.imbdapp.R
import com.example.imbdapp.application.MasterApp
import com.example.imbdapp.extensions.startAnimation
import com.example.imbdapp.extensions.stopAnimation
import com.example.imbdapp.utilities.PaginationScrollListener
import javax.inject.Inject

class HomeFragment : Fragment(), HomeRecyclerAdapter.MovieItemListener {
    @Inject
    lateinit var homeViewModel: HomeViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var adapter: HomeRecyclerAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MasterApp.rootFactory.getHomeComponent().inject(this)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = root.findViewById(R.id.moviesRecyclerView)
        swipeLayout = root.findViewById(R.id.moviewSwipeRefreshLayout)
        progressBar = root.findViewById(R.id.homeProgressBar)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        progressBar.startAnimation()

        layoutManager = GridLayoutManager(context,2)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        var isLoading: Boolean = false

        adapter = HomeRecyclerAdapter(requireContext(), this)
        homeViewModel.moviesData.observe(this, Observer {
            if (adapter.movies.isEmpty()) {
                adapter.newMovies(it)
                recyclerView.adapter = adapter
            } else {
                adapter.newMovies(it)
                adapter.notifyDataSetChanged()
            }
            homeViewModel.page.value = homeViewModel.page.value!! + 1
            progressBar.stopAnimation()
            swipeLayout.isRefreshing = false
            isLoading = false
        })

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager as GridLayoutManager) {
            override fun isLastPage(): Boolean {
                return false
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                progressBar.startAnimation()
                homeViewModel.nextPage()
            }
        })

        swipeLayout.setOnRefreshListener {
            if (!isLoading){
                isLoading = true
                homeViewModel.refreshMoviesData()
            }
        }

        return root
    }

    override fun onItemClicked(video: Movie) {
        homeViewModel.selectedMovie.value = video
        val bundle = Bundle()
        bundle.putParcelable("movieSelected", video)
        navController.navigate(R.id.action_nav_home_to_detailMovie, bundle)
    }
}