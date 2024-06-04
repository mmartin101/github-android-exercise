package com.maxmartin.github.api

import com.maxmartin.github.models.network.GithubUser
import com.maxmartin.github.models.network.GithubUserListUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface GithubAPI {
    @GET("users?per_page=30")
    suspend fun users(@Query("since") since: Long): Response<List<GithubUserListUser>>

    @GET("users/{username}")
    suspend fun users(@Path("username") username: String): Response<GithubUser>
}