package com.jasvir.freshworks.ui.adapter.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jasvir.freshworks.R
import com.jasvir.freshworks.persitence.SearchDB


class FavouriteAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var favList = ArrayList<SearchDB>()

    /**
     * interface for row click
     */
    interface OnClickListener {
        fun onRowClicked(data: SearchDB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_search -> FavViewHolder(
                view
            )
            else -> throw IllegalArgumentException(parent.context.getString(R.string.viewtype_creation_error))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_search -> (holder as FavViewHolder).bind(
                favList.get(position),
                listener
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_search
    }


    override fun getItemCount(): Int {
        return favList.size
    }

    /**
     * @param favList so that we can compare if it exists in favourite or not
     */
    fun updateList(favList: List<SearchDB>?) {
        if (favList == null) return
        this.favList = favList as ArrayList<SearchDB>
        notifyDataSetChanged()
    }

}
