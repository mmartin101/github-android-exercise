package com.maxmartin.github.models

data class User(
    val id: Long,
    val username: String,
    val avatarUrl: String,
    val name: String,
    val company: String,
    val blogUrl: String,
    val location: String,
    val bio: String,
    val twitterUsername: String,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
)