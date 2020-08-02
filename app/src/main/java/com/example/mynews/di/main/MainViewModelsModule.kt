package com.example.mynews.di.main

import androidx.lifecycle.ViewModel
import com.example.mynews.di.ViewModelKey
import com.example.mynews.ui.home.HomeViewModel
import com.example.mynews.ui.main.MainViewModel
import com.example.mynews.ui.world.WorldViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorldViewModel::class)
    abstract fun bindWorldViewModel(viewModel: WorldViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}