package com.maxmartin.github.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxmartin.github.repository.GithubUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class GithubUserListViewModel(
    private val repository: GithubUserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = MutableStateFlow(UserListState())
    val state = _state.asStateFlow()

    fun getUsers() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val result = repository.getUsers()
                if (result.isSuccess) {
                    _state.value = _state.value.copy(
                        loading = false,
                        userList = result.getOrDefault(emptyList())
                    )
                } else {
                    // TODO should we just use the exception and display a pre-canned string in the ui?
                    Timber.e(result.exceptionOrNull())
                    _state.value = _state.value.copy(
                        loading = false,
                        error = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }
}