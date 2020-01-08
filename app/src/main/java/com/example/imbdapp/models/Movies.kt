package com.example.imbdapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "cache")
data class Movies (@PrimaryKey(autoGenerate = true) val _id: Int?, @Json(name = "total_pages") val lastPage: Int, val results: List<Movie>)