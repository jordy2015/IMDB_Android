package com.example.imbdapp.viewModelUtilities

import com.example.imbdapp.ui.home.HomeFragment
import dagger.Component

@Component(modules = [HomeViewModule::class])
interface ModelComponent {
    fun inject(act: HomeFragment)
}