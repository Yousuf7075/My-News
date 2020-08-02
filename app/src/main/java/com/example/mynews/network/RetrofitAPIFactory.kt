package com.example.mynews.network

import android.content.Context
import com.example.mynews.BuildConfig
import com.example.mynews.R
import com.example.mynews.utils.AppConstants
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object RetrofitAPIFactory {


    private const val BASE_URL = AppConstants.BASE_URL
    private val TIME_OUT = 30
    private val cacheSize = (100 * 1024 * 1024).toLong()
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    private var cache: Cache? = null

    var gson = GsonBuilder()
        .setLenient()
        .create()

    internal fun createService(context: Context, timeOut: Int): Endpoints {
        return getRetrofit(context, timeOut)!!.create(Endpoints::class.java!!)
    }

    private fun getRetrofit(context: Context, timeOut: Int): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient(context, timeOut))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit
    }

    private fun getOkHttpClient(context: Context, timeOut: Int): OkHttpClient? {
        if (okHttpClient == null) {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            okHttpClientBuilder.writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

            okHttpClientBuilder.cache(getCache(context))

            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)


            okHttpClientBuilder.addInterceptor { chain -> chain.proceed(chain.request().newBuilder().build()) }

            val loggingInterceptor = LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(com.ihsanbal.logging.Level.BASIC)
                .log(Platform.INFO)
                .tag(context.getString(R.string.app_name))
                .request("Request")
                .response("Response")
                .executor(Executors.newSingleThreadExecutor())
                .build()

            okHttpClientBuilder.addInterceptor(loggingInterceptor)

            okHttpClient = okHttpClientBuilder.build()
        }

        return okHttpClient
    }

    fun getCache(context: Context): Cache? {
        if (cache == null) {
            val cacheDir = File(context.cacheDir, "okhttp-cache")
            cache = Cache(cacheDir, cacheSize)
        }
        return cache
    }

}
