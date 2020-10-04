package com.example.architecture_tutorial.viewModel

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.adapter.ViewHolder
import com.example.architecture_tutorial.model.Item
import com.example.architecture_tutorial.model.ItemDao
import com.example.architecture_tutorial.model.ItemDatabase
import com.example.architecture_tutorial.ui.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDatabase = ItemDatabase.getInstance(application)
    private val itemDao: ItemDao = itemDatabase.itemDao()
    private val items : LiveData<List<Item>> = itemDao.getAll()

    var id : Int? = null
    var message : String? = ""

    fun getAll(): LiveData<List<Item>>{
        return this.items
    }

    fun insert(id : Int?, message : String){
        if (message != ""){
            val item = Item(id, message)
            viewModelScope.launch(Dispatchers.IO){
                itemDao.insert(item)
            }
        } else{
            Toast.makeText(getApplication(), "Please input the message", Toast.LENGTH_LONG).show()
        }
    }

    fun update(item : Item){
        viewModelScope.launch(Dispatchers.IO){
            itemDao.update(item)
        }
    }

    fun delete(item : Item){
        viewModelScope.launch(Dispatchers.IO){
            itemDao.delete(item)
        }
    }

    fun allDelete(){
        viewModelScope.launch(Dispatchers.IO){
            itemDao.allDelete()
        }
    }


}