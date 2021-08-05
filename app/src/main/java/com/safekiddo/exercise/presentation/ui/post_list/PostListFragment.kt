package com.safekiddo.exercise.presentation.ui.post_list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.safekiddo.exercise.R
import com.safekiddo.exercise.adapter.RecyclerAdapter
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.util.OnPostClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : Fragment(), OnPostClickListener {

    private val viewModel: PostListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var addButton: FloatingActionButton
    private var posts: List<Post> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initFloatingButton()
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.getPosts().observe(viewLifecycleOwner){
            adapter.setPosts(it)
            setPosts(it)
        }
    }

    private fun setPosts(list: List<Post>){
        posts = list
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
        val bundle = Bundle()
        val post: Post = posts[pos]
        bundle.putParcelable("post", post)
        findNavController().navigate(R.id.showPostAction, bundle)
    }

    override fun onLingPostClick(pos: Int) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(view?.context, R.style.DialogStyle)
        alert.setTitle(R.string.alert)
        alert.setMessage(R.string.alert_message)
        alert.setPositiveButton(R.string.alert_yes
        ) { _, _ ->
            viewModel.deletePost(posts[pos])
        }
        alert.setNegativeButton(R.string.alert_no
        ) { dialog, _ ->
            dialog.dismiss()
        }
        alert.show()
    }
}