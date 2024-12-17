package com.example.t_project.data.datasource.internet

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

object HttpClient {

    private const val BASE_URL = "https://v2.jokeapi.dev/joke/"

//    @Inject
//    lateinit var api: Api
//
//    @Inject
//    lateinit var okHttpClient: OkHttpClient
//
//    @Inject
//    lateinit var retrofit: Retrofit


    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(RequestInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: Api = retrofit.create<Api>()
}