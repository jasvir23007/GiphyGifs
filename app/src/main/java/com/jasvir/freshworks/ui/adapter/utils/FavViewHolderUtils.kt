package com.jasvir.freshworks.ui.adapter.utils

import android.view.View
import com.jasvir.freshworks.R
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.utils.ImageUtils
import kotlinx.android.synthetic.main.item_search.view.*

/**
 * fun to setup fav views
 *
 * @param it item data which needs to be shown on views
 * @param itemView to get views to display data
 */
fun setupViewsFav(it: SearchDB, itemView: View) {
    it.url?.let { it1 -> ImageUtils.loadImage(it1, itemView.iv_thumb, itemView.context) }
    itemView.iv_Fav.setImageResource(R.drawable.ic_favorite_black_24dp)
}

