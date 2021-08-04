package com.safekiddo.exercise.repository

import androidx.lifecycle.LiveData
import com.safekiddo.exercise.domain.model.Post

interface PostRepository {
    fun getPostsFromApi()
    fun getPosts(): LiveData<List<Post>>
    fun insertPost(post: Post)
}