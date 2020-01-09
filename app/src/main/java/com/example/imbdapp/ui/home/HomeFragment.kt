package com.example.imbdapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.imbdapp.R
import com.example.imbdapp.application.MasterApp
import com.example.imbdapp.ui.tabs.TabAdapter
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MasterApp.rootFactory.getHomeComponent().inject(this)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val sectionsPagerAdapter = TabAdapter(childFragmentManager)
        val viewPager: ViewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = root.findViewById(R.id.moviesTab)
        tabs.setupWithViewPager(viewPager)
        return root
    }

}