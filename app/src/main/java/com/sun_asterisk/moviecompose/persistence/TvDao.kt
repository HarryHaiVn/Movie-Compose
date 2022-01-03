package com.sun_asterisk.moviecompose.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sun_asterisk.moviecompose.models.entities.Tv

@Dao
interface TvDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTv(tvs: List<Tv>)

  @Update
  suspend fun updateTv(tv: Tv)

  @Query("SELECT * FROM Tv WHERE id = :id_")
  suspend fun getTv(id_: Long): Tv?

  @Query("SELECT * FROM Tv WHERE page = :page_")
  suspend fun getTvList(page_: Int): List<Tv>
}
