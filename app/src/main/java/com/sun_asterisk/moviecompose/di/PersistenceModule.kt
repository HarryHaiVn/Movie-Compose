package com.sun_asterisk.moviecompose.di

import android.content.Context
import androidx.room.Room
import com.sun_asterisk.moviecompose.data.local.AppDatabase
import com.sun_asterisk.moviecompose.data.local.MovieDao
import com.sun_asterisk.moviecompose.data.local.PeopleDao
import com.sun_asterisk.moviecompose.data.local.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
    return Room
      .databaseBuilder(context, AppDatabase::class.java, "MovieCompose.db")
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
    return appDatabase.movieDao()
  }

  @Provides
  @Singleton
  fun provideTvDao(appDatabase: AppDatabase): TvDao {
    return appDatabase.tvDao()
  }

  @Provides
  @Singleton
  fun providePeopleDao(appDatabase: AppDatabase): PeopleDao {
    return appDatabase.peopleDao()
  }
}
