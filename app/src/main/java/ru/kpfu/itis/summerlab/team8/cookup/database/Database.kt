package ru.kpfu.itis.summerlab.team8.cookup.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kpfu.itis.summerlab.team8.cookup.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}