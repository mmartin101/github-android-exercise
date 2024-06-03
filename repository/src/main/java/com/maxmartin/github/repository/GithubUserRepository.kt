package com.maxmartin.github.repository

import com.maxmartin.github.models.ListUser
import com.maxmartin.github.models.User

interface GithubUserRepository {
    suspend fun getUsers(): Result<List<ListUser>>
    suspend fun getUser(username: String): Result<User>
}