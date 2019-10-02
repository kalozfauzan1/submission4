package com.kaloz.dicoding.submission4.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerTabAdapter(fm: FragmentManager,lifecycle: Lifecycle, fragments:MutableList<Fragment>) : FragmentStateAdapter(fm,lifecycle) {

    private val mFragments = fragments

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {

        return mFragments[position]
    }


}