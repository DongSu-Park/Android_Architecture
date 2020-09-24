package com.example.architecture_tutorial.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase(){
    abstract fun itemDao() : ItemDao

    companion object{
        private var instance: ItemDatabase? = null

        fun getInstance(context : Context) : ItemDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext,
                    ItemDatabase::class.java, "itemDb.db").allowMainThreadQueries().build()
            }

            return instance!!
        }
    }

}