package com.example.and_practice.di

import com.example.and_practice.data.remote.api.AuthApi
import com.example.and_practice.data.remote.api.ProfileApi
import com.example.and_practice.data.repository.AuthRepositoryImpl
import com.example.and_practice.data.repository.ProductRepositoryImpl
import com.example.and_practice.data.repository.ProfileRepositoryImpl
import com.example.and_practice.data.repository.WishRepositoryImpl
import com.example.and_practice.domain.repository.AuthRepository
import com.example.and_practice.domain.repository.ProductRepository
import com.example.and_practice.domain.repository.ProfileRepository
import com.example.and_practice.domain.repository.WishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
    ): AuthRepository = AuthRepositoryImpl(authApi)

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
    ): ProfileRepository = ProfileRepositoryImpl(profileApi)

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = ProductRepositoryImpl()

    @Provides
    @Singleton
    fun provideWishRepository(): WishRepository = WishRepositoryImpl()
}
