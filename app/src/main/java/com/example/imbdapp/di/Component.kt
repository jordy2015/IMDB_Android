package com.example.imbdapp.di

import com.example.imbdapp.ui.favorites.FavoritesFragment
import com.example.imbdapp.ui.watchlater.WatchLaterFragment
import com.example.imbdapp.ui.home.HomeFragment
import com.example.imbdapp.ui.movies.MoviesListFragment
import com.example.imbdapp.ui.search.SearchFragment
import dagger.Subcomponent

@Subcomponent
interface Component {
    fun inject(act: MoviesListFragment)
    fun inject(act: WatchLaterFragment)
    fun inject(act: SearchFragment)
    fun inject(act: FavoritesFragment)
    fun inject(act: HomeFragment)
}