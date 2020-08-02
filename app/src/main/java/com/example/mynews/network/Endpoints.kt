package com.example.mynews.network


import com.example.mynews.ui.home.model.HomeRP
import io.reactivex.Flowable
import retrofit2.http.GET

interface Endpoints{

    @GET("svc/topstories/v2/home.json?api-key=70NmBQwZYiI8y7JGhpTFfxVp2AUmI2v2")
    fun getHomeDataList(): Flowable<HomeRP>
}