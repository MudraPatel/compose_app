package com.example.testcomposeapp.data.services

import com.example.testcomposeapp.data.model.ListResponse
import retrofit2.Response
import retrofit2.http.GET

interface AppService {

    @GET("demos/marvel")
    suspend fun getList(): Response<List<ListResponse>>
}