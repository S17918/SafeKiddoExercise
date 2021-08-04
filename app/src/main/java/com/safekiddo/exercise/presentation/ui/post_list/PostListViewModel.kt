package com.safekiddo.exercise.presentation.ui.post_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    fun getRacesFromApi(){
        repository.getPostsFromApi()
    }

    fun getPosts(): LiveData<List<Post>> {
        return repository.getPosts()
    }

}