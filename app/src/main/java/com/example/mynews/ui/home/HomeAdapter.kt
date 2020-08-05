package com.example.mynews.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews.R
import com.example.mynews.databinding.HomeItemRowBinding
import com.example.mynews.ui.home.model.Result

class HomeAdapter(private val context: Context, private val homeItems: ArrayList<Result>): RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val homeItemBinding: HomeItemRowBinding = DataBindingUtil
            .inflate(layoutInflater, R.layout.home_item_row, parent, false)
        return HomeViewHolder(homeItemBinding)
    }

    override fun getItemCount(): Int {
        return homeItems.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeItems[position])
    }
}