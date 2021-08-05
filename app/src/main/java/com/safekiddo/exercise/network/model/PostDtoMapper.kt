package com.safekiddo.exercise.network.model

import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.domain.util.DomainMapper

class PostDtoMapper : DomainMapper<PostDto, Post> {
    override fun mapToDomainModel(model: PostDto): Post {
        return Post(
            id = model.id,
            title = model.title,
            description = model.description,
            featuredImage = model.featuredImage,
            favourite = false
        )
    }

    override fun mapFromDomainModel(domainModel: Post): PostDto {
        return PostDto(
            id = domainModel.id,
            title = domainModel.title,
            description = domainModel.description,
            featuredImage = domainModel.featuredImage,
        )
    }

    fun toDomainList(list: List<PostDto>): List<Post>{
        return list.map { mapToDomainModel(it) }
    }

    fun fromDomainList(list: List<Post>): List<PostDto>{
        return list.map { mapFromDomainModel(it) }
    }
}