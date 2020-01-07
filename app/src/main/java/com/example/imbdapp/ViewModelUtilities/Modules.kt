package com.example.imbdapp.ViewModelUtilities

import androidx.lifecycle.ViewModel
import com.example.imbdapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeViewModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(vm: HomeViewModel): ViewModel
}