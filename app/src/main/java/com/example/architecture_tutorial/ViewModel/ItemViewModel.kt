package com.example.architecture_tutorial.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemDao
import com.example.architecture_tutorial.Model.ItemDatabase


class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDatabase = ItemDatabase.getInstance(application)
    private val itemDao: ItemDao = itemDatabase.itemDao()
    private val items : LiveData<List<Item>> = itemDao.getAll()

    fun getAll(): LiveData<List<Item>>{
        return this.items
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