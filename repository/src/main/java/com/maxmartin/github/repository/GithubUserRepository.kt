package com.maxmartin.github.repository

import androidx.paging.PagingData
import com.maxmartin.github.models.ListUser
import com.maxmartin.github.models.User
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    fun getUsers(): Flow<PagingData<ListUser>>
    suspend fun getUser(username: String): Result<User>
}