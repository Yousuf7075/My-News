package com.example.mynews.network.dataSource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.mynews.network.Endpoints
import com.example.mynews.network.RetrofitAPIFactory
import com.example.mynews.ui.home.model.HomeRP
import com.example.mynews.utils.Resource
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(val context: Context) {


    private var endpoints: Endpoints? = null

    private var homeData: MediatorLiveData<Resource<out HomeRP>>? = null

    init {
        endpoints = RetrofitAPIFactory.createService(context, 30)
    }

    fun getHomeDataList(): LiveData<Resource<out HomeRP>>{
        homeData =  MediatorLiveData()
        homeData!!.value = Resource.loading(null)
        val homeRpDefault: HomeRP? = null
        val source: LiveData<Resource<out HomeRP>> =
            LiveDataReactiveStreams.fromPublisher(
                endpoints?.getHomeDataList()
                    ?.onErrorReturn {
                        homeRpDefault
                    }?.map {
                        if (homeRpDefault == null){
                            Resource.authenticated(it)
                        }else{
                            Resource.error("Error", null)
                        }
                    }
                    ?.subscribeOn(Schedulers.io())!!
            )
        homeData!!.addSource(source, Observer {
            homeData!!.postValue(it)
            homeData!!.removeSource(source)
        })
        return homeData as MediatorLiveData<Resource<out HomeRP>>
    }

    /*// Get livedata with LiveDataReactiveStream from Flowable
    fun getAreaDemo(): LiveData<Resource<out RPAreaList>> {
        if (areaList == null) {
            areaList = MediatorLiveData()
            areaList!!.value = Resource.loading(null)
            // A default Item that return in onError
            val rpAreaListDefault = RPAreaList()
            rpAreaListDefault.isError = false
            val source: LiveData<Resource<out RPAreaList>> =
                LiveDataReactiveStreams.fromPublisher(endpoints!!.getAreaList()
                    .onErrorReturn {
                        rpAreaListDefault
                    }.map {
                        if (rpAreaListDefault.isError.equals(false)) {
                            Resource.authenticated(it)
                        } else {
                            Resource.error("Error", null)
                        }

                    }
                    .subscribeOn(Schedulers.io())
                )

            areaList!!.addSource(source, Observer {
                areaList!!.postValue(it)
                areaList!!.removeSource(source)
            })
        }

        return areaList as MediatorLiveData<Resource<out RPAreaList>>
    }*/
}