package ru.kpfu.itis.summerlab.team8.cookup

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey val id: Long,
    val name: String,
    val urlImage: String,
    val description: String,
    val ingredients : String,
    val isFavourite: Boolean,
    val instructions: String = ""
)