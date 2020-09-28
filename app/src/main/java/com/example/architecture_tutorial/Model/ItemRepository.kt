package com.example.architecture_tutorial.Model

import android.app.Application
import androidx.lifecycle.LiveData

class ItemRepository(application: Application){
    private val itemDatabase = ItemDatabase.getInstance(application)
    private val itemDao: ItemDao = itemDatabase.itemDao()
    private val items : LiveData<List<Item>> = itemDao.getAll()

    fun getAll() : LiveData<List<Item>>{
        return items
    }

    fun insert(item : Item){
        itemDao.insert(item)
    }

    fun update(item : Item){
        itemDao.update(item)
    }

    fun delete(item : Item){
        itemDao.delete(item)
    }

    fun allDelete(){
        itemDao.allDelete()
    }
}