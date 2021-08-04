package com.safekiddo.exercise.network.responses

import com.google.gson.annotations.SerializedName
import com.safekiddo.exercise.network.models.PostDto

data class PostResponse(
    @SerializedName("posts")
    var posts: List<PostDto>
){
}