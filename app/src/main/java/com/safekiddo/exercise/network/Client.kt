package com.safekiddo.exercise.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    private val baseURL: String = "https://run.mocky.io/v3/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun buildService(): Service {
        return retrofit.create(Service::class.java)
    }
}