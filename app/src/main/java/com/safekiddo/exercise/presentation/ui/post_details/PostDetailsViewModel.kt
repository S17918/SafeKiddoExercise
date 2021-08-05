package com.safekiddo.exercise.presentation.ui.post_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    fun getPosts(): LiveData<List<Post>> {
        return repository.getPosts()
    }

    fun updatePost(post: Post){
        repository.updatePost(post)
    }
}