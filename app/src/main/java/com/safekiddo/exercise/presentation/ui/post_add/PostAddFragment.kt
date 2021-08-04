package com.safekiddo.exercise.presentation.ui.post_add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safekiddo.exercise.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostAddFragment : Fragment() {

    private lateinit var viewModel: PostAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostAddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}