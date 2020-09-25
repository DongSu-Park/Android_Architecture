package com.example.architecture_tutorial.adapter.Presenter

import android.content.Context
import com.example.architecture_tutorial.Model.Item

interface ViewHolderPresenter {
    fun deleteDB(context: Context, targetId : Int?, targetMessage: String?): List<Item>
}