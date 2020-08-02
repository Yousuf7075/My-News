package com.example.mynews.di

import androidx.lifecycle.ViewModelProvider
import com.example.mynews.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Mohammad Yousuf on August, 2020
 *
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}