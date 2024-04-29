package com.example.pokeapp.di

import com.example.pokeapp.data.repository.PokeRepositoryImpl
import com.example.pokeapp.domain.repository.PokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokeRepository(
        pokeRepositoryImpl: PokeRepositoryImpl
    ): PokeRepository
}