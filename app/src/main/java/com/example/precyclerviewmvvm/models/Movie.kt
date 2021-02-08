package com.example.precyclerviewmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val is_new: Int,
    val language: String,
    val like_percent: Int,
    val rating: String,
    val title: String,
    val type: String,
    val vote_count: Int
)