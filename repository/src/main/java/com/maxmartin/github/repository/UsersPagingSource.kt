package com.maxmartin.github.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxmartin.github.api.GithubAPI
import com.maxmartin.github.models.ListUser

class UsersPagingSource(private val api: GithubAPI) : PagingSource<Long, ListUser>() {
    private var lastid = 0L
    override fun getRefreshKey(state: PagingState<Long, ListUser>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ListUser> {
        return try {
            val page = params.key ?: 0
            val response = api.users(since = page)

            val users = response.body().toListUsers()
            lastid = users.last().id
            LoadResult.Page(
                data = users,
                prevKey = if (page == 0L) null else lastid,
                nextKey = if (response.body().isNullOrEmpty()) null else lastid.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}