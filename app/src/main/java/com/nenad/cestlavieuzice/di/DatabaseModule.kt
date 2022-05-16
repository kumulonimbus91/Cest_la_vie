package com.nenad.cestlavieuzice.di

import android.content.Context
import androidx.room.Room
import com.nenad.cestlavieuzice.database.DishDatabase
import com.nenad.cestlavieuzice.utils.Constants.DATABASE_DISH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DishDatabase::class.java, DATABASE_DISH).build()

    @Singleton
    @Provides
    fun provideDatabaseDao(dishDatabase: DishDatabase) = dishDatabase.getDao()
}