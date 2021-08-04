package com.safekiddo.exercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.safekiddo.exercise.R
import com.safekiddo.exercise.domain.model.Post
import com.safekiddo.exercise.util.OnPostClickListener
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val clickListener: OnPostClickListener): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var posts: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        if(posts.isNotEmpty()){
            return posts.size
        }
        return 0
    }

    fun setPosts(list: List<Post>){
        posts = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val clickListener: OnPostClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private var postTitle: TextView? = null
        private var postIcon: ImageView? = null

        init {
            postTitle = itemView.findViewById(R.id.post_title)
            postIcon = itemView.findViewById(R.id.post_icon)
            itemView.setOnClickListener(this)
        }

        fun bind(post: Post){
            postTitle?.text = post.title
            Picasso.get().load(post.featuredImage?.replace("https", "http")?.replace("http", "https")).into(postIcon)

//            if(post.featuredImage?.substring(0,3) == "http"){
//                Picasso.get().load(post.featuredImage).into(postIcon)
//            }else{
//                println("ERROR")
//            }
        }

        override fun onClick(v: View?) {
            clickListener.onPostClick(adapterPosition)
        }
    }
}