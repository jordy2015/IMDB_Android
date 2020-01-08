package com.example.imbdapp.viewModelUtilities

import com.example.imbdapp.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent
interface ModelComponent {
    fun inject(act: HomeFragment)
}