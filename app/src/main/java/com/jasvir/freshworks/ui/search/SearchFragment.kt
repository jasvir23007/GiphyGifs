package com.jasvir.freshworks.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.jasvir.freshworks.R
import com.jasvir.freshworks.api.NetworkState
import com.jasvir.freshworks.base.BaseFragment
import com.jasvir.freshworks.extension.afterTextChangedDelayed
import com.jasvir.freshworks.extension.gone
import com.jasvir.freshworks.extension.visible
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.ui.adapter.search.SearchAdapter
import com.jasvir.freshworks.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.layout_header_search.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchFragment : BaseFragment(), SearchAdapter.OnClickListener {
    @LayoutRes
    override fun getLayoutResId() = R.layout.search_fragment

    private val repositoryRecyclerViewAdapter = SearchAdapter(this)
    private val model by sharedViewModel<SearchViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModelData()
        setupSearchViewListener()
    }

    /**
     * setup search listeners for gif
     *
     */
    private fun setupSearchViewListener() {
        et_search.afterTextChangedDelayed {
            it.let { model.fetchByGif(it) }
        }
    }

    /**
     * observe data from viewmodel
     *
     */
    private fun observeViewModelData() {
        model.networkState?.observe(
            this,
            Observer {
                if (it == NetworkState.SUCCESS)
                    fragment_progress_bar.gone()
                else
                    fragment_progress_bar.visible()
            })
        model.data.observe(this, Observer { repositoryRecyclerViewAdapter.submitList(it) })
        model.updateFavData.observe(
            this,
            Observer { repositoryRecyclerViewAdapter.updateFavList(it) })

    }

    /**
     * setup recyclerview for search however i have added delay
     * to add favourite list as it was taking some time for observers
     * to setup and to other objects to intialise and to avoid a delay has been added
     *
     */
    private fun setupRecyclerView() {
        fragment_recycler_view.adapter = repositoryRecyclerViewAdapter
        Handler(Looper.myLooper()!!).postDelayed({
            repositoryRecyclerViewAdapter.updateFavList(model.getAllPersistent())
        }, 1000)
    }


    override fun onRowClicked(data: SearchDB) {
        model.handleFav(data)
        repositoryRecyclerViewAdapter.notifyDataSetChanged()
    }


}

