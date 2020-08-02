package com.example.mynews.network

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mynews.network.dataSource.LocalDataSource
import com.example.mynews.network.dataSource.RemoteDataSource
import com.example.mynews.ui.home.model.HomeRP
import com.example.mynews.utils.Resource
import javax.inject.Inject


/**
 * Created by Mohammad Yousuf on August, 2020
 *
 */

class Repository @Inject constructor(context: Context) {


    //Initialize Sqlite
    private val localDataSource = LocalDataSource(context)
    private val remoteDataSource = RemoteDataSource(context)

    fun getHomeDataList(): LiveData<Resource<out HomeRP>> {
        return remoteDataSource.getHomeDataList()
    }


    /*fun createNewEngine(engin: Engin): Long?{
       return localDataSource.createEngineLocal(engin)
    }
    fun insertAll(enginList: List<Engin>){
         localDataSource.insertAllLocal(enginList)
    }

    fun updateEngine(engin: Engin){
        localDataSource.updateLocal(engin)
    }

    fun getAllEngin():LiveData<List<Engin>>{
        return localDataSource.getAllEngineLocal()
    }

    fun getAreaDemo():LiveData<Resource<out RPAreaList>>{
       return remoteDataSource.getAreaDemo()
    }*/

}