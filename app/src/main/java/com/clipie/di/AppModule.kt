package com.clipie.di

import android.content.Context
import android.content.SharedPreferences
import com.clipie.data.common.SharedPreferenceName
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedPreferenceName.LOCAL_SHARED_PREFERENCES.preferenceName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}