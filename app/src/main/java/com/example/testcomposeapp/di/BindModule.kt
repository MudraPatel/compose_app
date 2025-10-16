package com.example.testcomposeapp.di

import com.example.testcomposeapp.data.repository.AppRepository
import com.example.testcomposeapp.data.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Singleton
    @Binds
    abstract fun bindAppRepository(repositoryImpl: AppRepositoryImpl): AppRepository
}