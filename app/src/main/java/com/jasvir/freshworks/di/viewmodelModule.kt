package com.jasvir.freshworks.di

import com.jasvir.freshworks.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * class contains modules for koin dependency injection
 * and are used in viewmodel
 */
val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}
