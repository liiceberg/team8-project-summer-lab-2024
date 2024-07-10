package ru.kpfu.itis.summerlab.team8.cookup.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.kpfu.itis.summerlab.team8.cookup.recipe.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

}