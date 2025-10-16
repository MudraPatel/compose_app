package com.example.testcomposeapp.data.repository

import com.example.testcomposeapp.data.model.ListResponse
import com.example.testcomposeapp.data.network.NetworkOperation
import com.example.testcomposeapp.data.services.AppService
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(val appService: AppService) : AppRepository {

    override suspend fun getList(): NetworkOperation<List<ListResponse>> {
         return appService.getList().run{
             if(isSuccessful){
                 NetworkOperation.Success(data = body() ?: emptyList())
             }else{
                 NetworkOperation.Failure(reason = message())
             }
         }
    }
}