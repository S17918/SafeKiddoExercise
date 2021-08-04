package com.safekiddo.exercise.network.response

import com.google.gson.annotations.SerializedName
import com.safekiddo.exercise.network.model.PostDto

data class PostResponse(
    @SerializedName("posts")
    var posts: List<PostDto>
){
}