package com.example.herewegoagain.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    val movieName: String,
    val movieGenre: String,
    val movieRating: Int
): Parcelable