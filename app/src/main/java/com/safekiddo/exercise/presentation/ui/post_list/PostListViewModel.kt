package com.safekiddo.exercise.presentation.ui.post_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    fun getPosts(): LiveData<List<Post>> {
        return repository.getPosts()
    }

    fun deletePost(post: Post){
        return repository.deletePost(post)
    }

}