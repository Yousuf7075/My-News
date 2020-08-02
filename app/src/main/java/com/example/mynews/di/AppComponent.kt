package com.example.mynews.di


import android.app.Application
import com.example.mynews.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


/**
 * Created by Mohammad Yousuf on August, 2020
 *
 * */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class, AppModule::class, ViewModelFactoryModule::class])

interface AppComponent : AndroidInjector<NewsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}