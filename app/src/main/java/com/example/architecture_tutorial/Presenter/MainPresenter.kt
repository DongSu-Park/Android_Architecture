package com.example.architecture_tutorial.Presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemDatabase
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.UI.MainActivity
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter(private val view: MainActivity) : Presenter {
    lateinit var items : List<Item>

    override fun loadDB(context : Context) {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        items = db.itemDao().getAll()
        view.layout_recyclerView.run{
            adapter = ItemAdapter(context, items)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun addDB(context : Context) {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        val newId : Int? = null
        val newMessage : String = view.et_input.text.toString()

        val newItem = Item(newId, newMessage)

        db.itemDao().insert(newItem)
        items = db.itemDao().getAll()
        view.layout_recyclerView.run {
            adapter = ItemAdapter(context, items)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun clearDB(context : Context) {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        db.itemDao().allDelete()
        items = db.itemDao().getAll()

        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.run {
            setTitle("삭제 메세지")
            setMessage("전체 메세지를 삭제하겠습니까?")
            setPositiveButton("확인") { dialog, width ->
                view.layout_recyclerView.run{
                    adapter = ItemAdapter(context, items)
                    adapter?.notifyDataSetChanged()
                }
            }
            setNegativeButton("취소") { dialog, width -> }
            show()
        }
    }

}