package com.example.architecture_tutorial.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_Entity")
data class Item(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id : Int?,

    @ColumnInfo(name = "message")
    var message : String?
){
    constructor() : this(null, "")
}