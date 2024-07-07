package ru.kpfu.itis.summerlab.team8.cookup.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.kpfu.itis.summerlab.team8.cookup.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Insert
    fun insert(recipe: Recipe)

}