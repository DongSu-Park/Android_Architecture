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
    private var presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Layout Setting
        layout_recyclerView.run{
            adapter = ItemAdapter(context, presenter.loadDB(context))
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        // Input Button Click Event
        btn_input.setOnClickListener {
            val newId : Int? = null
            val newMessage : String = et_input.text.toString()

            layout_recyclerView.adapter = ItemAdapter(this, presenter.addDB(this@MainActivity, newId, newMessage))
            layout_recyclerView.adapter?.notifyDataSetChanged()
        }

        // List Clear Button Click Event
        btn_clear.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this, R.style.CustomMaterialDialog)
            builder.run {
                setTitle("삭제 메세지")
                setMessage("전체 메세지를 삭제하겠습니까?")
                setPositiveButton("확인") { dialog, width ->

                    layout_recyclerView.adapter = ItemAdapter(context, presenter.clearDB(context))
                    layout_recyclerView.adapter?.notifyDataSetChanged()
                }
                setNegativeButton("취소") { dialog, width -> }
                show()
            }
        }
    }
}