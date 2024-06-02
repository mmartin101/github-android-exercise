package com.maxmartin.github.api

import com.maxmartin.github.models.network.GithubUser
import com.maxmartin.github.models.network.GithubUserListUser
import retrofit2.Response
import retrofit2.http.GET

interface GithubAPI {
    @GET("users")
    fun users(): Response<List<GithubUserListUser>>

    @GET("users/{username}")
    fun users(username: String): Response<GithubUser>
}