package com.safekiddo.exercise.repository

import androidx.lifecycle.LiveData
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.model.PostDtoMapper
import com.safekiddo.exercise.persistance.PostDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val service: Service,
    private val mapper: PostDtoMapper,
    private val database: PostDatabase
) : PostRepository {

    override fun getPostsFromApi() {
        CoroutineScope(IO).launch {
            try{
                mapper.toDomainList(service.getPostsFromApi().posts).forEach {
                    insertPost(it)
                }
            }catch (throwable: Throwable){
                when(throwable){
                    is IOException -> println("EXCEPTION: IOException")
                    is HttpException -> println("EXCEPTION: HttpException")
                    else -> println("EXCEPTION: UnknownException")
                }
            }
        }
    }

    override fun getPosts(): LiveData<List<Post>> {
        return database.roomPostDao().getPosts()
    }

    override fun insertPost(post: Post) {
        CoroutineScope(IO).launch {
            database.roomPostDao().insertPost(post)
        }
    }

    override fun deletePost(post: Post) {
        CoroutineScope(IO).launch {
            database.roomPostDao().deletePost(post)
        }
    }
}