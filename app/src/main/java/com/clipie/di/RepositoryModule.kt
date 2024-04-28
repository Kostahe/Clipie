package com.clipie.di

import com.clipie.authentication.data.repository.AuthenticationRepositoryImpl
import com.clipie.authentication.domain.repository.AuthenticationRepository
import com.clipie.main.data.repository.ChatRepositoryImpl
import com.clipie.main.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    @Singleton
    fun bindChatRepository(messagesRepositoryImpl: ChatRepositoryImpl): ChatRepository
}