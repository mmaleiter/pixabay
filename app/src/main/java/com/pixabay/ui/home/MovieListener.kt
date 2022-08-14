package com.pixabay.ui.home


interface MovieListener {

    fun onImageDataClicked(imageItem: PixBayUiListItem)

    fun onFavouriteClicked(imageItem: PixBayUiListItem)
}