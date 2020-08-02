package com.example.mynews.di

import com.example.mynews.di.main.MainFragmentBuildersModule
import com.example.mynews.di.main.MainModule
import com.example.mynews.di.main.MainScope
import com.example.mynews.di.main.MainViewModelsModule
import com.example.mynews.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {
    @MainScope
    @JvmSuppressWildcards
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}