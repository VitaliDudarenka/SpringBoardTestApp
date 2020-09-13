package com.gmail.dudarenka.vitali.springboardtestapp.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.dudarenka.vitali.springboardtestapp.R
import com.gmail.dudarenka.vitali.springboardtestapp.screen.adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.pagesLoaded.observe(this, Observer<PageAdapter> {
            if (it != null) {
                page_recycler.adapter = viewModel.adapter
                val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                page_recycler.layoutManager = layoutManager
            }
        })

        add_button.setOnClickListener {
            viewModel.onAddButtonClicked((page_recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
        }

        reload_button.setOnClickListener{
            viewModel.onReloadButtonClicked()
        }

    }


}