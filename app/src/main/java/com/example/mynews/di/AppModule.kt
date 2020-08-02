package com.example.mynews.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.mynews.network.Repository
import com.example.mynews.utils.AppConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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