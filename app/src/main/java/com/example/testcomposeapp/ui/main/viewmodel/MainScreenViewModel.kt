package com.example.testcomposeapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcomposeapp.data.model.ListResponse
import com.example.testcomposeapp.data.network.NetworkOperation
import com.example.testcomposeapp.data.usecase.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getListUseCase: GetListUseCase): ViewModel() {

    private val _list = MutableStateFlow<List<ListResponse>>(emptyList())
    val list = _list.asStateFlow()

    fun getList(){
        getListUseCase.invoke().onEach {
            when(it){
                is NetworkOperation.Failure -> {}
                is NetworkOperation.Loading -> {}
                is NetworkOperation.Success -> {
                    _list.emit(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}