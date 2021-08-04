package com.safekiddo.exercise.repository

import com.safekiddo.exercise.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}