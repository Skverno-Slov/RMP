package com.example.orm.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface PlayerDao {
    @Query(value = "SELECT * FROM players")
    fun getPlayers(): LiveData<List<Player>>

    @Insert
    fun addPlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)

    @Query(value = "DELETE FROM players WHERE playerId = :id")
    fun deletePlayerById(id: Int)

    @Update
    fun updatePlayer(player: Player)
}