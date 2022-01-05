package com.sun_asterisk.moviecompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sun_asterisk.moviecompose.data.models.entities.Movie
import com.sun_asterisk.moviecompose.data.models.entities.Person
import com.sun_asterisk.moviecompose.data.models.entities.Tv
import com.sun_asterisk.moviecompose.data.local.converters.IntegerListConverter
import com.sun_asterisk.moviecompose.data.local.converters.KeywordListConverter
import com.sun_asterisk.moviecompose.data.local.converters.ReviewListConverter
import com.sun_asterisk.moviecompose.data.local.converters.StringListConverter
import com.sun_asterisk.moviecompose.data.local.converters.VideoListConverter

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
