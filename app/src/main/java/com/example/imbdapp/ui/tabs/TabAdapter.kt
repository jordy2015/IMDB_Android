package com.example.imbdapp.ui.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.imbdapp.ui.favorites.FavoritesFragment
import com.example.imbdapp.ui.movies.MoviesListFragment


class TabAdapter(private val context: FragmentManager) : FragmentPagerAdapter(context) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0)  MoviesListFragment() else FavoritesFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Este año" else "Favoritas"
    }

    override fun getCount(): Int {
        return 2
    }
}