package com.safekiddo.exercise.presentation.ui.post_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.safekiddo.exercise.R
import com.safekiddo.exercise.adapter.RecyclerAdapter
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.network.model.PostDto
import com.safekiddo.exercise.util.OnPostClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostListFragment : Fragment(), OnPostClickListener {

    private val viewModel: PostListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var addButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getApiData()
        initRecyclerView()
        initFloatingButton()
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.getPosts().observe(viewLifecycleOwner){
            adapter.setPosts(it)
        }
    }

    private fun getApiData() {
        viewModel.getRacesFromApi()
    }

    private fun initRecyclerView(){
        recyclerView = requireView().findViewById(R.id.post_list)
        adapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(object : DividerItemDecoration(context, VERTICAL){

        })
    }

    private fun initFloatingButton(){
        addButton = requireView().findViewById(R.id.addPostButton)
        addButton.setOnClickListener {
            findNavController().navigate(R.id.addNewPostAction)
        }
    }

    override fun onPostClick(pos: Int) {
        val bundle: Bundle = Bundle()
        bundle.putInt("pos", pos)
        findNavController().navigate(R.id.showPostAction, bundle)
    }
}