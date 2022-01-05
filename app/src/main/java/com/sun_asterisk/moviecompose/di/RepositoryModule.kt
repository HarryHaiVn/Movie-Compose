package com.sun_asterisk.moviecompose.di

import com.sun_asterisk.moviecompose.data.remote.service.MovieService
import com.sun_asterisk.moviecompose.data.remote.service.PeopleService
import com.sun_asterisk.moviecompose.data.remote.service.TheDiscoverService
import com.sun_asterisk.moviecompose.data.remote.service.TvService
import com.sun_asterisk.moviecompose.data.local.MovieDao
import com.sun_asterisk.moviecompose.data.local.PeopleDao
import com.sun_asterisk.moviecompose.data.local.TvDao
import com.sun_asterisk.moviecompose.data.repository.DiscoverRepository
import com.sun_asterisk.moviecompose.data.repository.MovieRepository
import com.sun_asterisk.moviecompose.data.repository.PeopleRepository
import com.sun_asterisk.moviecompose.data.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideDiscoverRepository(
    discoverService: TheDiscoverService,
    movieDao: MovieDao,
    tvDao: TvDao
  ): DiscoverRepository {
    return DiscoverRepository(discoverService, movieDao, tvDao)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(
    movieService: MovieService,
    movieDao: MovieDao
  ): MovieRepository {
    return MovieRepository(movieService, movieDao)
  }

  @Provides
  @ViewModelScoped
  fun providePeopleRepository(
    peopleService: PeopleService,
    peopleDao: PeopleDao
  ): PeopleRepository {
    return PeopleRepository(peopleService, peopleDao)
  }

  @Provides
  @ViewModelScoped
  fun provideTvRepository(
    tvService: TvService,
    tvDao: TvDao
  ): TvRepository {
    return TvRepository(tvService, tvDao)
  }
}
