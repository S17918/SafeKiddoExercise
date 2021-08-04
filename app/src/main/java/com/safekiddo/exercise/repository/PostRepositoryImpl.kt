package com.safekiddo.exercise.repository

import androidx.lifecycle.LiveData
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.model.PostDtoMapper
import com.safekiddo.exercise.persistance.PostDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PostRepositoryImpl(
    private val service: Service,
    private val mapper: PostDtoMapper,
    private val database: PostDatabase
) : PostRepository {

    override fun getPostsFromApi() {
        CoroutineScope(IO).launch {
            mapper.toDomainList(service.getPostsFromApi().posts).forEach {
                insertPost(it)
            }
        }
    }

    override fun getPosts(): LiveData<List<Post>> {
        return database.roomPostDao().getPosts()
    }

    override fun insertPost(post: Post) {
        database.roomPostDao().insertPost(post)
    }
}