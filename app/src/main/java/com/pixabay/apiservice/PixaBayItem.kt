package com.pixabay.apiservice

import com.google.gson.annotations.SerializedName
import com.pixabay.ui.base.ListAdapterItem
import java.io.Serializable

data class PixaBayItem (
    override val id: Long,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Long,
    val previewHeight: Long,
    val webformatURL: String,
    val webformatWidth: Long,
    val webformatHeight: Long,
    val largeImageURL: String,
    val fullHDURL: String? = "",
    val imageURL: String? = "",
    val imageWidth: Long,
    val imageHeight: Long,
    val imageSize: Long,
    val views: Long,
    val downloads: Long,
    val likes: Long,
    val comments: Long,

    @SerializedName("user_id")
    val userID: Long,

    val user: String,
    val userImageURL: String,
) : ListAdapterItem, Serializable


