package com.example.herewegoagain.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete


@Dao
interface MovieDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query( "DELETE FROM  movie_table")
    fun deleteAllMovies()

    @Query( "SELECT * FROM movie_table ORDER BY movieId ASC")
    fun readAllData(): LiveData<List<Movie>>

}

