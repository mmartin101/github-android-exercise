package com.maxmartin.github.user_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maxmartin.github.models.ListUser

@Composable
fun UserListHost(viewModel: GithubUserListViewModel, modifier: Modifier) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    UserList(
        modifier = modifier,
        loading = state.loading,
        listUsers = state.userList
    )
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    loading: Boolean,
    listUsers: List<ListUser>,
    preview: Boolean = false
) {
    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(listUsers) { user ->
                UserListItem(listUser = user, preview = preview)
            }
        }
    }
}

@Composable
fun UserListItem(listUser: ListUser, preview: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (preview) {
            Box(modifier = Modifier
                .size(100.dp)
                .background(color = Color.Cyan))
        } else {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(listUser.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = listUser.username
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = listUser.username, fontSize = 18.sp)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun UserListLoadingPreview() {
    Scaffold {
        UserList(loading = true, listUsers = emptyList())
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun UserListPreview() {

    val listUsers = mutableListOf<ListUser>()
    for (i in 0..25) {
        listUsers.add(ListUser(
            id = 1,
            username = "mmartin101",
            avatarUrl = ""
        ))
    }
    Scaffold {
        UserList(loading = false, listUsers = listUsers, preview = true)
    }
}

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    UserListItem(
        listUser = ListUser(
            id = 1,
            username = "mmartin101",
            avatarUrl = ""
        ),
        preview = true
    )
}