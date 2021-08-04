package com.safekiddo.exercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.safekiddo.exercise.R
import com.safekiddo.exercise.network.Client
import com.safekiddo.exercise.network.model.PostDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Client().buildService()

        CoroutineScope(IO).launch {
            val response = service.getPosts()

            for(post: PostDto in response.posts){
                println(post.description)
            }
        }

    }
}