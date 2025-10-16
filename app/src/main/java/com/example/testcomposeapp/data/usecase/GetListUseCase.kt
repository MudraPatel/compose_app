package com.example.testcomposeapp.data.usecase

import com.example.testcomposeapp.data.model.ListResponse
import com.example.testcomposeapp.data.network.NetworkOperation
import com.example.testcomposeapp.data.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListUseCase @Inject constructor(private val repository: AppRepository) {

    operator fun invoke(): Flow<NetworkOperation<List<ListResponse>>> = flow{
        emit(repository.getList())
    }
}