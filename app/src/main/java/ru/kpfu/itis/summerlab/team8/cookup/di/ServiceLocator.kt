package ru.kpfu.itis.summerlab.team8.cookup.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kpfu.itis.summerlab.team8.cookup.database.Database

object ServiceLocator {
    private var dbInstance: Database? = null

    fun initDatabase(ctx: Context) {
        println("ajefjkfesjf")
        dbInstance = Room.databaseBuilder(ctx, Database::class.java, "recipes")
            .createFromAsset("database/recipes.db")
            .build()
    }

    fun getDbInstance(): Database = dbInstance ?: throw IllegalStateException("Db is not initialize")
}