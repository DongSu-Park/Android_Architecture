package com.example.architecture_tutorial.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecture_tutorial.Presenter.MainPresenter
import com.example.architecture_tutorial.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var presenter = MainPresenter(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Layout Setting
        presenter.loadDB(this@MainActivity)

        // Input Button Click Event
        btn_input.setOnClickListener {
            presenter.addDB(this@MainActivity)
        }

        // List Clear Button Click Event
        btn_clear.setOnClickListener {
           presenter.clearDB(this@MainActivity)
        }
    }
}