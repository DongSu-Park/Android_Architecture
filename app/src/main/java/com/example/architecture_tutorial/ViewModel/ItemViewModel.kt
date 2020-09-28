package com.example.architecture_tutorial.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemRepository


class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ItemRepository(application)
    private val items = repository.getAll()

    fun getAll(): LiveData<List<Item>>{
        return this.items
    }

    fun insert(item : Item){
        repository.insert(item)
    }

    fun update(item : Item){
        repository.update(item)
    }

    fun delete(item : Item){
        repository.delete(item)
    }

    fun allDelete(){
        repository.allDelete()
    }



}