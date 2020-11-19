package com.jasvir.freshworks.ui.adapter.utils

import android.view.View
import com.jasvir.freshworks.R
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.utils.ImageUtils
import kotlinx.android.synthetic.main.item_search.view.*

/**
 * fun to setup search views
 *
 * @param favList to check is item fav
 * @param it item data which needs to be shown on views
 * @param itemView to get views to display data
 */
fun setupViews(favList: List<SearchDB>, it: SearchDB, itemView: View) {
    it.url?.let { it1 -> ImageUtils.loadImage(it1, itemView.iv_thumb, itemView.context) }
    var isAdded = false
    for (fav in favList) {
        if (it.id.equals(fav.id, true)) {
            itemView.iv_Fav.setImageResource(R.drawable.ic_favorite_black_24dp)
            it.isFav = 1
            isAdded = true
            break
        }
    }

    if (!isAdded) {
        it.isFav = 0
        itemView.iv_Fav.setImageResource(R.drawable.ic_favorite_white_24dp)
    }
}

