package com.safekiddo.exercise.network

import com.safekiddo.exercise.network.response.PostResponse
import retrofit2.http.GET

interface Service {
    @GET("6125f2d0-0688-4547-aae8-0295d984f196")
    suspend fun getPosts(): PostResponse
}