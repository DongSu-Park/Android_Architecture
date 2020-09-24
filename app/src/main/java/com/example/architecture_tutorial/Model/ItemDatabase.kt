package com.example.architecture_tutorial.Model

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ItemDatabase : RoomDatabase(){
    abstract fun itemDao() : ItemDao
    private var instance: ItemDatabase? = null

    private fun getInstance(context : Context) : ItemDatabase{
        if (instance == null){
            instance = Room.databaseBuilder(context.applicationContext,
                ItemDatabase::class.java, "itemDb.db").build()
        }

        return instance!!
    }
}