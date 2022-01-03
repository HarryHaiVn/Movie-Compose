package com.sun_asterisk.moviecompose.di

import com.sun_asterisk.moviecompose.network.service.MovieService
import com.sun_asterisk.moviecompose.network.service.PeopleService
import com.sun_asterisk.moviecompose.network.service.TheDiscoverService
import com.sun_asterisk.moviecompose.network.service.TvService
import com.sun_asterisk.moviecompose.persistence.MovieDao
import com.sun_asterisk.moviecompose.persistence.PeopleDao
import com.sun_asterisk.moviecompose.persistence.TvDao
import com.sun_asterisk.moviecompose.repository.DiscoverRepository
import com.sun_asterisk.moviecompose.repository.MovieRepository
import com.sun_asterisk.moviecompose.repository.PeopleRepository
import com.sun_asterisk.moviecompose.repository.TvRepository
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
