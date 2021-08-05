package com.safekiddo.exercise.presentation.ui.post_details

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.safekiddo.exercise.R
import com.safekiddo.exercise.domain.model.Post
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private val viewModel: PostDetailsViewModel by viewModels()
    private var pos: Int = 0
    private lateinit var postImage: ImageView
    private lateinit var postTitle: TextView
    private lateinit var postDescription: TextView
    private lateinit var postDescriptionCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.post_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pos = arguments?.getInt("pos")!!
        initImageView()
        initTextViews()
        getPost()
    }

    private fun getPost() {
        viewModel.getPosts().observe(viewLifecycleOwner){
            setImageView(it[pos])
            setTextViews(it[pos])
        }
    }

    private fun initImageView(){
        postImage = view?.findViewById(R.id.details_post_icon)!!
    }

    private fun setImageView(post: Post){
        Picasso.get().load(post.featuredImage?.replace("https", "http")?.replace("http", "https")).into(postImage)
    }

    private fun initTextViews(){
        postTitle = view?.findViewById(R.id.details_post_title)!!
        postDescription = view?.findViewById(R.id.details_post_description)!!
        postDescriptionCount = view?.findViewById(R.id.details_post_description_count)!!
    }

    private fun setTextViews(post: Post){
        postTitle.text = post.title
        postDescription.text = post.description
        postDescriptionCount.text = post.description?.count().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu, menu)
    }
}