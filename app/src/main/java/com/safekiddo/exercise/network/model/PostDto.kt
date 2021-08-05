package com.safekiddo.exercise.network.model

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("icon")
    var featuredImage: String
) {
}