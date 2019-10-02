package com.kaloz.dicoding.submission4.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.kaloz.dicoding.submission4.R
import java.io.Console

class MovieContainerFragment constructor(contentLayoutId: Int) : Fragment(contentLayoutId) {

    constructor() : this(R.layout.fragment_movie_container)

    private var tab:TabLayout? = null
    private var tabLastIndex=0
    private val listFragment:MutableList<Fragment> = ArrayList()

    companion object{
        fun createFragment():Fragment{
            return MovieContainerFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tab = view.findViewById(R.id.tabs_movie)
        tab?.addTab(tab?.newTab()!!.setIcon(R.drawable.ic_subscriptions_white_24dp))
        tab?.addTab(tab?.newTab()!!.setIcon(R.drawable.ic_favorite_white))


        listFragment.add(MoviesFragment.createFragment())
        listFragment.add(FavMoviesFragment.createFragment())
        childFragmentManager.beginTransaction().replace(R.id.container,listFragment[0]).commit()

        tab?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
           override fun onTabReselected(tab: TabLayout.Tab?) {

           }

           override fun onTabUnselected(tab: TabLayout.Tab?) {


           }

           override fun onTabSelected(tab: TabLayout.Tab?) {
               tabLastIndex = tab?.position ?: 0
               when(tab?.position){
                   0->{
                       childFragmentManager.beginTransaction().replace(R.id.container,listFragment[tab.position]).commit()
                   }
                   1->{
                       childFragmentManager.beginTransaction().replace(R.id.container,listFragment[tab.position]).commit()
                   }
               }
           }

       })
    }

    override fun onResume() {
        super.onResume()
        tab?.selectTab(tab?.getTabAt(tabLastIndex))
        childFragmentManager.beginTransaction().replace(R.id.container,listFragment[tabLastIndex]).commit()
    }
}