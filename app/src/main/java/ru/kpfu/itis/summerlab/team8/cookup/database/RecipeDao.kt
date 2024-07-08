package ru.kpfu.itis.summerlab.team8.cookup.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kpfu.itis.summerlab.team8.cookup.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

}