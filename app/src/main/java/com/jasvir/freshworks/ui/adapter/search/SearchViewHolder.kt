package com.jasvir.freshworks.ui.adapter.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jasvir.freshworks.R
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.ui.adapter.utils.setupViews
import kotlinx.android.synthetic.main.item_search.view.*
/**
 * View holder for search adapter
 *
 * @param parent itemview for displaying data
 */
class SearchViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(favList:List<SearchDB>,
        search: SearchDB?,
        listener: SearchAdapter.OnClickListener
    ) {
        search?.let {
            setupViews(favList,it, itemView)
            setListeners(listener, search)
        }
    }

    private fun setListeners(
        listener: SearchAdapter.OnClickListener,
        search: SearchDB
    ) {
        itemView.item_parent.setOnClickListener {

            if (search.isFav == 0) {
                itemView.iv_Fav.setImageResource(R.drawable.ic_favorite_black_24dp)
                search.isFav = 1
            }else {
                itemView.iv_Fav.setImageResource(R.drawable.ic_favorite_white_24dp)
                search.isFav = 0
            }

            search.let { it1 ->
                listener.onRowClicked(it1)

            }
        }
    }


}
