package com.jasvir.freshworks.ui.search

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.jasvir.freshworks.R
import com.jasvir.freshworks.base.BaseActivity
import com.jasvir.freshworks.ui.adapter.home.HomeViewpagerAdapter
import com.jasvir.freshworks.ui.favourite.FavFragment
import kotlinx.android.synthetic.main.activity_main_search.*

class SearchActivity : BaseActivity(false) {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main_search
    private val homeViewpagerAdapter = HomeViewpagerAdapter(supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewPager()
    }

    /**
     * func to setup viewPager
     *
     */
    private fun setUpViewPager() {
        homeViewpagerAdapter.addFragment(SearchFragment(), getString(R.string.home))
        homeViewpagerAdapter.addFragment(FavFragment(),getString(R.string.favourite))
        view_pager_home.adapter = homeViewpagerAdapter
        tab_home.setupWithViewPager(view_pager_home)
    }


}
