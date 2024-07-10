package ru.kpfu.itis.summerlab.team8.cookup.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey val id: Long,
    val name: String,
    val urlImage: String,
    val description: String,
    val ingredients : String,
    var isFavourite: Boolean,
    val instructions: String = ""
)