package com.example.mynews.ui.home

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mynews.R
import com.example.mynews.databinding.HomeItemRowBinding
import com.example.mynews.ui.home.model.Result

class HomeViewHolder(private val homeItemBinding: HomeItemRowBinding) : RecyclerView.ViewHolder(homeItemBinding.root) {
    fun bind(item: Result){

        // Use Glide library to upload the artwork image
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.ic_placholder_home_image)
            .error(R.drawable.ic_placholder_home_image)

        Glide.with(homeItemBinding.root.context)
            .load(item.multimedia[0].url)
            .apply(options)
            .into(homeItemBinding.homeItemIV)

        homeItemBinding.homeItemTitleTV.text = item.title
        homeItemBinding.categoryTV.text = item.section
        getTime(item.publishedDate)
    }

    private fun getTime(publishedDate: String) {
        Log.e("Time", publishedDate)
    }
}