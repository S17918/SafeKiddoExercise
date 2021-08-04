package com.safekiddo.exercise.network.models

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("icon")
    var featuredImage: String? = null
) {
}