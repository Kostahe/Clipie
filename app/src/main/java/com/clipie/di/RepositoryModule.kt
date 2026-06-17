package com.clipie.di

import com.clipie.app.data.repository.SessionRepositoryImpl
import com.clipie.app.domain.repository.SessionRepository
import com.clipie.authentication.data.repository.AuthenticationRepositoryImpl
import com.clipie.main.data.repository.MessagesRepositoryImpl
import com.clipie.authentication.domain.repository.AuthenticationRepository
import com.clipie.main.domain.repository.MessagesRepository
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
    fun bindMessagesRepository(messagesRepositoryImpl: MessagesRepositoryImpl): MessagesRepository

    @Binds
    @Singleton
    fun bindSessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository
}