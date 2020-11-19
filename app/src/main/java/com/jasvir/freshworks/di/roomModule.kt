package com.jasvir.freshworks.di

import com.jasvir.freshworks.persitence.AppDataBase
import org.koin.dsl.module

/**
 * class contains modules for koin dependency injection
 * and are used in room DB
 */
val roomModule = module {
    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getDao() }
}