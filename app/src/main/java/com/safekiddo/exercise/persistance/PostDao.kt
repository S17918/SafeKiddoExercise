package com.safekiddo.exercise.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.safekiddo.exercise.domain.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(post: Post): Long

    @Delete
    fun deletePost(post: Post)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePost(post: Post)
}