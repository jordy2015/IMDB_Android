package com.example.imbdapp.ViewModelUtilities

import com.example.imbdapp.ui.home.HomeFragment
import dagger.Component

@Component(modules = [HomeViewModule::class])
interface ModelComponent {
    fun inject(act: HomeFragment)
}