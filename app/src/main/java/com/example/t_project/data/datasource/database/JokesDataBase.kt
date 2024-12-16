package com.example.t_project.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.t_project.data.entity.database.LocalJoke
import com.example.t_project.data.entity.database.RemoteJoke
import javax.inject.Inject

@Database(entities = [LocalJoke::class, RemoteJoke::class], version = 2, exportSchema = false)
abstract class JokesDataBase : RoomDatabase() {
    abstract fun jokeDao() : JokeDao
    companion object {
        @Volatile
        @Inject
        lateinit var INSTANCE: JokesDataBase
        fun initDatabase(context: Context) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                JokesDataBase::class.java,
                "jokes_database"
            ).fallbackToDestructiveMigration().build()
            INSTANCE = instance
        }
    }
}