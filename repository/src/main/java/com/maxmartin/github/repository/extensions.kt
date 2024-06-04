package com.maxmartin.github.repository

import com.maxmartin.github.models.ListUser
import com.maxmartin.github.models.User
import com.maxmartin.github.models.network.GithubUser
import com.maxmartin.github.models.network.GithubUserListUser

internal fun List<GithubUserListUser>?.toListUsers(): List<ListUser> =
    this?.map { githubListUser ->
        ListUser(
            id = githubListUser.id,
            username = githubListUser.login,
            avatarUrl = githubListUser.avatar_url
        )
    } ?: emptyList()

internal fun GithubUser.toUser(): User = User(
    id = id,
    username = login,
    avatarUrl = avatar_url,
    name = name ?: "",
    bio = bio ?: "",
    location = location ?: "",
    company = company ?: "",
    blogUrl = blog ?: "",
    followers = followers,
    following = following,
    publicGists = public_gists,
    publicRepos = public_repos,
    twitterUsername = twitter_username ?: ""
)