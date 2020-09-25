package com.example.architecture_tutorial.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_tutorial.Presenter.MainPresenter
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var presenter = MainPresenter(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Layout Setting
        layout_recyclerView.run {
            val loadItem = presenter.loadDB(context)

            adapter = ItemAdapter(context, loadItem)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        // Input Button Click Event
        btn_input.setOnClickListener {
            layout_recyclerView.run {
                val newId : Int? = null
                val newMessage : String = et_input.text.toString()
                val addItem = presenter.addDB(context, newId, newMessage)

                adapter = ItemAdapter(context, addItem)
                adapter?.notifyDataSetChanged()

                et_input.text = null
            }

        }

        // List Clear Button Click Event
        btn_clear.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this@MainActivity, R.style.CustomMaterialDialog)
            builder.run {
                setTitle("삭제 메세지")
                setMessage("전체 메세지를 삭제하겠습니까?")
                setPositiveButton("확인") { dialog, width ->
                    layout_recyclerView.run{
                        val clearItem = presenter.clearDB(context)

                        adapter = ItemAdapter(context, clearItem)
                        adapter?.notifyDataSetChanged()
                    }
                }
                setNegativeButton("취소") { dialog, width -> }
                show()
            }
        }
    }
}