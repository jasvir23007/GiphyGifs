package com.jasvir.freshworks.diTest

import com.jasvir.freshworks.persitence.SearchDao
import com.jasvir.freshworks.repo.SearchRepo
import org.koin.dsl.module

fun repoMockedDBModule(dao: SearchDao) = module {
    factory { SearchRepo(get(), dao) }
}