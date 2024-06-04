package com.maxmartin.github.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.maxmartin.github.models.ListUser
import com.maxmartin.github.repository.GithubUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class GithubUserListViewModel(
    private val repository: GithubUserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userState = MutableStateFlow(UserState(false))
    val userState = _userState.asStateFlow()

    fun getUsers(): Flow<PagingData<ListUser>> = repository.getUsers()

    fun getUser(username: String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                try {
                    val user = repository.getUser(username)
                    _userState.value = userState.value.copy(
                        user = user.getOrNull()
                    )
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }
}