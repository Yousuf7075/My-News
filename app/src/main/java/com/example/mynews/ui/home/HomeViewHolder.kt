package com.example.mynews.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews.databinding.HomeItemRowBinding
import com.example.mynews.ui.home.model.Result

class HomeViewHolder(private val homeItemBinding: HomeItemRowBinding) : RecyclerView.ViewHolder(homeItemBinding.root) {
    fun bind(item: Result){
        Glide.with(homeItemBinding.root.context).load(item.multimedia[0].url).into(homeItemBinding.homeItemIV)
        homeItemBinding.homeItemTitleTV.text = item.title
        homeItemBinding.categoryTV.text = item.section
    }
}