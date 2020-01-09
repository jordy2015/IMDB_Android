package com.example.imbdapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imbdapp.ui.favorites.FavoritesViewModel
import com.example.imbdapp.ui.watchlater.WatchLaterViewModel
import com.example.imbdapp.ui.home.HomeViewModel
import com.example.imbdapp.ui.movies.MoviesListViewModel
import com.example.imbdapp.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun mViewModel(viewModel: MoviesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WatchLaterViewModel::class)
    abstract fun wlViewModel(viewModel: WatchLaterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun sViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun fViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun hViewModel(viewModel: HomeViewModel): ViewModel
}


