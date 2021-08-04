package com.safekiddo.exercise.repository

import androidx.lifecycle.LiveData
import com.safekiddo.exercise.domain.models.Post
import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.models.PostDtoMapper

class PostRepositoryImpl(
    private val service: Service,
    private val mapper: PostDtoMapper
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return mapper.toDomainList(service.getPosts().posts)
    }
}