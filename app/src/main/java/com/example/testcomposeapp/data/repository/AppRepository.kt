package com.example.testcomposeapp.data.repository

import com.example.testcomposeapp.data.model.ListResponse
import com.example.testcomposeapp.data.network.NetworkOperation

interface AppRepository {
    suspend fun getList(): NetworkOperation<List<ListResponse>>
}