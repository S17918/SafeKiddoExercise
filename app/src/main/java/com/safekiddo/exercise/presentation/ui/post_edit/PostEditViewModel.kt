package com.safekiddo.exercise.presentation.ui.post_edit

import androidx.lifecycle.ViewModel
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostEditViewModel @Inject constructor(private val repository: PostRepository): ViewModel() {
    fun updatePost(post: Post){
        repository.updatePost(post)
    }
}