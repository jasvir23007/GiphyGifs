package com.jasvir.freshworks.ui.adapter.favourite

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.ui.adapter.utils.setupViewsFav
import kotlinx.android.synthetic.main.item_search.view.*

/**
 * View holder for favourite adapter
 *
 * @param parent itemview for displaying data
 */
class FavViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        search: SearchDB?,
        listener: FavouriteAdapter.OnClickListener
    ) {
        search?.let {
            setupViewsFav(it, itemView)
            setListeners(listener, search)
        }
    }

    private fun setListeners(
        listener: FavouriteAdapter.OnClickListener,
        search: SearchDB
    ) {
        itemView.item_parent.setOnClickListener {
            search.isFav = 0
            search.let { it1 -> listener.onRowClicked(it1) }
        }
    }


}
