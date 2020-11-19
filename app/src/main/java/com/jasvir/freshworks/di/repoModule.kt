package com.jasvir.freshworks.di

import com.jasvir.freshworks.repo.SearchRepo
import org.koin.dsl.module

/**
 * class contains modules for koin dependency injection
 * and are used in repository
 */
val repositoryModule = module {
    factory { SearchRepo(get(), get()) }
}