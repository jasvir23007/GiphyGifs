package com.jasvir.freshworks.ui.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jasvir.freshworks.R
import com.jasvir.freshworks.persitence.SearchDB


class SearchAdapter(private val listener: OnClickListener) :
    PagedListAdapter<SearchDB, RecyclerView.ViewHolder>(diffCallback) {
    private var favList = ArrayList<SearchDB>()

    interface OnClickListener {
        fun onRowClicked(data: SearchDB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_search -> SearchViewHolder(view)
            else -> throw IllegalArgumentException(parent.context.getString(R.string.viewtype_creation_error))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_search -> (holder as SearchViewHolder).bind(
                favList,
                getItem(position),
                listener
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_search

    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }


    fun updateFavList(listFav: List<SearchDB>?) {
        if (listFav == null) return
        favList = listFav as ArrayList<SearchDB>
        notifyDataSetChanged()

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<SearchDB>() {
            override fun areItemsTheSame(oldItem: SearchDB, newItem: SearchDB): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: SearchDB, newItem: SearchDB): Boolean =
                oldItem == newItem
        }
    }
}
