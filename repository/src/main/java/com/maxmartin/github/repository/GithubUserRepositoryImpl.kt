package com.maxmartin.github.repository

import com.maxmartin.github.api.GithubAPI
import com.maxmartin.github.models.ListUser
import com.maxmartin.github.models.User
import com.maxmartin.github.models.network.GithubUser
import com.maxmartin.github.models.network.GithubUserListUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class GithubUserRepositoryImpl(
    private val githubAPI: GithubAPI,
    private val dispatcher: CoroutineDispatcher
) : GithubUserRepository {
    override suspend fun getUsers(): Result<List<ListUser>> {
        return withContext(dispatcher) {
            try {
                val response = githubAPI.users()
                if (response.isSuccessful) {
                    Result.success(response.body().toListUsers())
                } else {
                    Result.failure(response.errorBody().toException())
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getUser(username: String): Result<User> {
        return withContext(dispatcher) {
            try {
                val response = githubAPI.users(username)
                // TODO: is there a better way to handle empty response body?
                val user = response.body()?.toUser()
                if (response.isSuccessful && user != null) {
                    Result.success(user)
                } else {
                    Result.failure(response.errorBody().toException())
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun ResponseBody?.toException(): Exception = Exception(toString())
    private fun List<GithubUserListUser>?.toListUsers(): List<ListUser> =
        this?.map { githubListUser ->
            ListUser(
                id = githubListUser.id,
                username = githubListUser.login
            )
        } ?: emptyList()

    private fun GithubUser.toUser(): User = User(
        id = id,
        username = login
    )
}