package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.adapter.RecyclerViewAdapter
import com.example.paging3.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter =recyclerViewAdapter
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collect {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}