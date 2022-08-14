package com.pixabay.ui.base

import android.view.View
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.imageview.ShapeableImageView
import com.pixabay.R

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("manageState")
fun manageState(progressBar: ProgressBar, state: Boolean) {
    progressBar.visibility = if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("setImage")
fun setImage(imageView: ShapeableImageView, image: String) {
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)
        .signature(ObjectKey(image))
    Glide.with(imageView.context)
        .load(image.toUri())
        .apply(requestOptions)
        .into(imageView)
}

@BindingAdapter("setFavouriteCondition")
fun setFavouriteCondition(imageView: ShapeableImageView, isFavourite: Boolean) {
    if (isFavourite) {
        imageView.setImageResource(R.drawable.ic_favorite_filled)
    } else {
        imageView.setImageResource(R.drawable.ic_favorite_border)
    }

}