package com.sun_asterisk.moviecompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sun_asterisk.moviecompose.data.models.entities.Person

@Dao
interface PeopleDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPeople(people: List<Person>)

  @Update
  suspend fun updatePerson(person: Person)

  @Query("SELECT * FROM people WHERE id = :id_")
  suspend fun getPerson(id_: Long): Person

  @Query("SELECT * FROM People WHERE page = :page_")
  suspend fun getPeople(page_: Int): List<Person>
}
