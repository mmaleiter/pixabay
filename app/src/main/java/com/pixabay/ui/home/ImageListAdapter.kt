package com.pixabay.ui.home

import com.pixabay.R
import com.pixabay.databinding.ItemPixabayBinding
import com.pixabay.ui.base.BaseAdapter

class ImageListAdapter(
    private val movieListener: MovieListener
) : BaseAdapter<ItemPixabayBinding, PixBayUiListItem>() {

    override val layoutId: Int = R.layout.item_pixabay

    override fun bind(binding: ItemPixabayBinding, item: PixBayUiListItem) {
        binding.apply {
            pixaBayItem = item
            listener = movieListener
            executePendingBindings()
        }
    }
}

