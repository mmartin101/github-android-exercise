package com.maxmartin.github.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxmartin.github.api.GithubAPI
import com.maxmartin.github.models.ListUser
import com.maxmartin.github.models.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import timber.log.Timber

class GithubUserRepositoryImpl(
    private val githubAPI: GithubAPI,
    private val dispatcher: CoroutineDispatcher
) : GithubUserRepository {
    override fun getUsers(): Flow<PagingData<ListUser>> {
        return Pager(
            config = PagingConfig(30),
            pagingSourceFactory = { UsersPagingSource(api = githubAPI) }
        ).flow.flowOn(dispatcher)
    }

    override suspend fun getUser(username: String): Result<User> {
        return withContext(dispatcher) {
            try {
                val response = githubAPI.users(username)
                val user = response.body()?.toUser()
                if (response.isSuccessful && user != null) {
                    Result.success(user)
                } else {
                    Result.failure(response.errorBody().toException())
                }
            } catch (e: Exception) {
                Timber.e(e)
                Result.failure(e)
            }
        }
    }

    private fun ResponseBody?.toException(): Exception = Exception(toString())
}