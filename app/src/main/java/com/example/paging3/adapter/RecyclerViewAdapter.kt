package com.example.paging3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3.R
import com.example.paging3.data.Result
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewAdapter :
    PagingDataAdapter<Result, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Result) {
            view.tvName.text = item.name
            view.tvDesc.text = item.species
            Glide.with(view.imageView)
                .load(item.image)
                .circleCrop()
                .into(view.imageView)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }

    }
}