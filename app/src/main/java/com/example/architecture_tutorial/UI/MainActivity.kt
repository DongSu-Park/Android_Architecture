package com.example.architecture_tutorial.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemDatabase
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity() {
    private lateinit var items : List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db : ItemDatabase = ItemDatabase.getInstance(this)

        // 현재 상태의 아이템을 DB 에서 가져옴
        items = db.itemDao().getAll()

        // Layout Setting
        layout_recyclerView.run{
            adapter = ItemAdapter(context, items)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        btn_input.setOnClickListener {
            val id : Int? = null
            val newMessage : String = et_input.text.toString()

            val item = Item(id, newMessage)
            db.itemDao().insert(item)

            // 추가 후의 DB 전체를 다시 가져옴
            items = db.itemDao().getAll()
            layout_recyclerView.adapter = ItemAdapter(this, items)
            layout_recyclerView.adapter?.notifyDataSetChanged()
        }

        btn_clear.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this, R.style.CustomMaterialDialog)
            builder.run {
                setTitle("삭제 메세지")
                setMessage("전체 메세지를 삭제하겠습니까?")
                setPositiveButton("확인") { dialog, width ->
                    db.itemDao().allDelete()
                    items = db.itemDao().getAll()
                    layout_recyclerView.adapter = ItemAdapter(context, items)
                    layout_recyclerView.adapter?.notifyDataSetChanged()
                }
                setNegativeButton("취소") { dialog, width -> }
                show()
            }
        }
    }
}