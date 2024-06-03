package com.maxmartin.github.user_list

import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userListModule = module {
    viewModel { GithubUserListViewModel(get(), Dispatchers.IO) }
}