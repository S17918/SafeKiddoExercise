package com.safekiddo.exercise.repository

import com.safekiddo.exercise.domain.models.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}