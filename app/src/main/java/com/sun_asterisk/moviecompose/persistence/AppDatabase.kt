package com.sun_asterisk.moviecompose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sun_asterisk.moviecompose.models.entities.Movie
import com.sun_asterisk.moviecompose.models.entities.Person
import com.sun_asterisk.moviecompose.models.entities.Tv
import com.sun_asterisk.moviecompose.persistence.converters.IntegerListConverter
import com.sun_asterisk.moviecompose.persistence.converters.KeywordListConverter
import com.sun_asterisk.moviecompose.persistence.converters.ReviewListConverter
import com.sun_asterisk.moviecompose.persistence.converters.StringListConverter
import com.sun_asterisk.moviecompose.persistence.converters.VideoListConverter

@Database(
  entities = [(Movie::class), (Tv::class), (Person::class)],
  version = 3, exportSchema = false
)
@TypeConverters(
  value = [
    (StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvDao(): TvDao
  abstract fun peopleDao(): PeopleDao
}
