package com.safekiddo.exercise.repository

import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.model.PostDtoMapper

class PostRepositoryImpl(
    private val service: Service,
    private val mapper: PostDtoMapper
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return mapper.toDomainList(service.getPosts().posts)
    }
}