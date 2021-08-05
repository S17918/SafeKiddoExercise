package com.safekiddo.exercise.presentation.ui.post_details

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.safekiddo.exercise.R
import com.safekiddo.exercise.domain.model.Post
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var post: Post
    private lateinit var postImage: ImageView
    private lateinit var postTitle: TextView
    private lateinit var postDescription: TextView
    private lateinit var postDescriptionCount: TextView
    private var optionMenu: Menu? = null

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
        post = arguments?.getParcelable("post")!!
        initImageView()
        initTextViews()
        setImageView(post)
        setTextViews(post)
    }

    private fun initImageView(){
        postImage = view?.findViewById(R.id.details_post_icon)!!
    }

    private fun setImageView(post: Post){
        Picasso.get().load(post.featuredImage.replace("https", "http").replace("http", "https")).into(postImage)
    }

    private fun initTextViews(){
        postTitle = view?.findViewById(R.id.details_post_title)!!
        postDescription = view?.findViewById(R.id.details_post_description)!!
        postDescriptionCount = view?.findViewById(R.id.details_post_description_count)!!
    }

    private fun setTextViews(post: Post){
        postTitle.text = post.title
        postDescription.text = post.description
        postDescriptionCount.text = post.description.count().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        if(post.favourite){
            menu.findItem(R.id.liked).isVisible = true
            menu.findItem(R.id.like).isVisible = false
        }else{
            menu.findItem(R.id.liked).isVisible = false
            menu.findItem(R.id.like).isVisible = true
        }

        optionMenu = menu
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit -> {
                val bundle = Bundle()
                bundle.putParcelable("post", post)
                findNavController().navigate(R.id.postEditAction, bundle)
            }
            R.id.like -> {
                item.isVisible = false
                optionMenu?.findItem(R.id.liked)?.isVisible = true
                post.favourite = true
                viewModel.updatePost(post)
            }
            R.id.liked ->{
                item.isVisible = false
                optionMenu?.findItem(R.id.like)?.isVisible = true
                post.favourite = false
                viewModel.updatePost(post)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}