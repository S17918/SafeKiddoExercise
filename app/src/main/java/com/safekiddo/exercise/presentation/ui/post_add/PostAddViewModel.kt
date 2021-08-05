package com.safekiddo.exercise.presentation.ui.post_add

import androidx.lifecycle.ViewModel
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostAddViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    fun insertPost(post: Post){
        repository.insertPost(post)
    }
}