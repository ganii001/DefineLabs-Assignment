package com.example.definelabsassignment.di

import android.content.Context
import androidx.room.Room
import com.example.definelabsassignment.network.local.dao.DaoHelper
import com.example.definelabsassignment.network.local.dao.DaoHelperImpl
import com.example.definelabsassignment.network.local.dao.DaoService
import com.example.definelabsassignment.network.local.database.CreatingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CreatingDatabase =
        Room.databaseBuilder(context, CreatingDatabase::class.java, "MyDB").build()

    @Provides
    @Singleton
    fun provideDao(creatingDatabase: CreatingDatabase): DaoService = creatingDatabase.daoService()

    @Provides
    @Singleton
    fun provideDaoHelper(daoHelper: DaoHelperImpl): DaoHelper = daoHelper

}