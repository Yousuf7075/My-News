package com.example.mynews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynews.network.Repository
import com.example.mynews.ui.home.model.HomeRP
import com.example.mynews.utils.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getHomeRP(): LiveData<Resource<out HomeRP>>{
        return repository.getHomeDataList();
    }
}