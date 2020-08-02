package com.example.mynews.network.dataSource

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class LocalDataSource(val context: Context) {


    /*var engineDao: EnginDao? = EnginDatabase.getDatabase(context)?.enginDao()



    fun deleteLocal(engin: Engin){
        DeleteAsync(engineDao).execute(engin)
    }
    fun updateLocal(engin: Engin){
        UpdateAsync(engineDao).execute(engin)
    }

    fun createEngineLocal(engin: Engin): Long?{
        return CreateAsync(engineDao).execute(engin).get()
    }

    fun insertAllLocal(engineList: List<Engin>):Unit?{
        return CreateAllAsync(engineDao).execute(engineList).get()
    }

    fun getAllEngineLocal(): LiveData<List<Engin>> {
        return GetAllEngineAsync(engineDao).execute().get()
    }
    class CreateAllAsync(engineDao: EnginDao?): AsyncTask<List<Engin>, Void, Unit>(){
        var engineDao = engineDao
        override fun doInBackground(vararg params: List<Engin>): Unit? {
            return engineDao?.insertAll(params[0])
        }

    }
    class GetAllEngineAsync(enginDao: EnginDao?): AsyncTask<Engin, Void, LiveData<List<Engin>>>(){
        var engineDao = enginDao
        override fun doInBackground(vararg params: Engin): LiveData<List<Engin>>? {
            return engineDao?.getAllEngin()
        }

    }
    //background oparation of creating engin
    class CreateAsync(enginDao: EnginDao?): AsyncTask<Engin, Void, Long?>(){
        var engineDao = enginDao
        override fun doInBackground(vararg params: Engin): Long? {
            return engineDao?.insert(params[0])
        }

    }
    //background oparation of deleting engin
    class DeleteAsync(enginDao: EnginDao?): AsyncTask<Engin, Void, Unit?>(){
        var engineDao = enginDao
        override fun doInBackground(vararg params: Engin): Unit? {
            return engineDao?.delete(params[0])
        }

    }

    class UpdateAsync(engineDao: EnginDao?): AsyncTask<Engin, Void, Unit?>(){
        var engineDao = engineDao
        override fun doInBackground(vararg params: Engin): Unit? {
            return engineDao?.update(params[0])
        }

    }*/
}