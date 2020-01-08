package com.example.imbdapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imbdapp.ui.watchlater.WatchLaterViewModel
import com.example.imbdapp.ui.home.HomeViewModel
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
    @ViewModelKey(HomeViewModel::class)
    abstract fun hViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WatchLaterViewModel::class)
    abstract fun wlViewModel(viewModel: WatchLaterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun slViewModel(viewModel: SearchViewModel): ViewModel
}


