package com.example.orm.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "players")
class Player {
    @PrimaryKey(autoGenerate = true)
    @NonNull()
    @ColumnInfo(name = "playerId")

    var id: Int = 0
    var name: String? = null
    var level: Int? = null
    @Ignore
    var ignored: Boolean = true

    constructor() {}

    constructor(id: Int, name: String, level:Int){
        this.id = id
        this.name = name
        this.level = level
    }

    constructor(name: String, level:Int){
        this.name = name
        this.level = level
    }
}