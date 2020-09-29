package com.example.architecture_tutorial.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao{
    @Query("SELECT * FROM item_Entity ORDER BY id ASC")
    fun getAll() : LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item : Item)

    @Update
    fun update(item : Item)

    @Delete
    fun delete(item : Item)

    @Query("DELETE FROM item_Entity")
    fun allDelete()
}