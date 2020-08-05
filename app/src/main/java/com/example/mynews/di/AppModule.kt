package com.example.mynews.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.mynews.R
import com.example.mynews.network.Repository
import com.example.mynews.utils.AppConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Mohammad Yousuf on August, 2020
 *
 */
@Module
 class AppModule {


    @Singleton
    @Provides
    open fun provideRetrofitInstance(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(application: Application?): Repository {
        return Repository(application!!.applicationContext)
    }

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions? {
        return RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application?,
        requestOptions: RequestOptions?
    ): RequestManager? {
        return Glide.with(application!!)
            .setDefaultRequestOptions(requestOptions!!)
    }

    /*@Singleton
    @Provides
    fun provideAppDrawable(application: Application?): Drawable? {
        return ContextCompat.getDrawable(application!!, R.drawable.engine)
    }


    @Singleton
    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideDatabaseReference(auth: FirebaseAuth): DatabaseReference? {
        return FirebaseStore.getDatabase(auth.currentUser!!.uid)
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    @Named("usersRef")
    fun provideUsersCollectionReference(rootRef: FirebaseFirestore): CollectionReference {
        return rootRef.collection("users")
    }*/




}