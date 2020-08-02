package com.example.mynews.di.main


import com.example.mynews.ui.home.HomeFragment
import com.example.mynews.ui.world.WorldFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment


    @ContributesAndroidInjector
    internal abstract fun contributeWorldFragment(): WorldFragment
}