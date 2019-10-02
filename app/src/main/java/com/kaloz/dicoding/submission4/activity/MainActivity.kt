package com.kaloz.dicoding.submission4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kaloz.dicoding.submission4.R
import com.kaloz.dicoding.submission4.adapter.PagerTabAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import android.content.Intent
import androidx.fragment.app.Fragment
import com.kaloz.dicoding.submission4.fragment.MovieContainerFragment
import com.kaloz.dicoding.submission4.fragment.TvShowContainerFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        initView()
        super.onCreate(savedInstanceState)
    }

    private fun initView(){
        val movieFragment = MovieContainerFragment.createFragment()
        val tvShowsFragment = TvShowContainerFragment.createFragment()

        val fragments:MutableList<Fragment> = ArrayList()
        fragments.add(movieFragment)
        fragments.add(tvShowsFragment)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.now_showing)
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        view_pager.adapter = PagerTabAdapter(supportFragmentManager,lifecycle,fragments)

        TabLayoutMediator(tabs,view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position){
                    0->tab.text=getString(R.string.movies)
                    1->tab.text=getString(R.string.tv_shows)
                }
            }).attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_change_settings) {
            val mIntent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
