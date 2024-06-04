package com.maxmartin.github.user_list

import com.maxmartin.github.models.User

data class UserState(
    val loading: Boolean,
    val user: User? = null
)