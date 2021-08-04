package com.safekiddo.exercise.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val featuredImage: String? = null
) : Parcelable {
}