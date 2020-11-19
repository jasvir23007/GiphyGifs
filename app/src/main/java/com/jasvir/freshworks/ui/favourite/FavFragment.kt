package com.jasvir.freshworks.ui.favourite

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.jasvir.freshworks.R
import com.jasvir.freshworks.base.BaseFragment
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.ui.adapter.favourite.FavouriteAdapter
import com.jasvir.freshworks.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fav_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavFragment : BaseFragment(), FavouriteAdapter.OnClickListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.fav_fragment
    private val repositoryRecyclerViewAdapter =
        FavouriteAdapter(this)
    private val model by sharedViewModel<SearchViewModel>()

    override fun onRowClicked(data: SearchDB) {
        model.handleFav(data)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observerUpdateFav()
    }

    /**
     * setup Recycler view
     *
     */
    private fun setupRecyclerView() {
        fav_recycler_view.adapter = repositoryRecyclerViewAdapter
        Handler(Looper.myLooper()!!).postDelayed({
            repositoryRecyclerViewAdapter.updateList(model.getAllPersistent())
        }, 1000)
    }

    /**
     * observe if an item is added to fav
     *
     */
    private fun observerUpdateFav() {
        model.updateFavData.observe(this, Observer { repositoryRecyclerViewAdapter.updateList(it) })
    }


}