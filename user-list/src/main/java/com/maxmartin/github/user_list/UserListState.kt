package com.maxmartin.github.user_list

import com.maxmartin.github.models.ListUser

data class UserListState(
    val loading: Boolean = true,
    val userList: List<ListUser> = emptyList(),
    val error: String? = null
)