package com.maxmartin.github.repository

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    single<GithubUserRepository> { GithubUserRepositoryImpl(get(), Dispatchers.IO) }
}