package com.example.t_project.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.models.database.LocalJoke
import com.example.t_project.domain.models.database.RemoteJoke

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteAll(remoteJokes: List<RemoteJoke>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocal(localJoke: LocalJoke)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalAll(localJokes: List<LocalJoke>)

    @Query("SELECT * FROM localJokes WHERE id = :jokeId")
    fun getLocalJokeById(jokeId: String): LocalJoke?

    @Query("SELECT * FROM remoteJokes WHERE id = :jokeId")
    fun getRemoteJokeById(jokeId: String): RemoteJoke?

    @Query("SELECT * FROM remoteJokes")
    fun getAllRemoteJokes(): List<RemoteJoke>

    @Query("SELECT * FROM localJokes")
    fun getAllLocalJokes(): List<LocalJoke>
}