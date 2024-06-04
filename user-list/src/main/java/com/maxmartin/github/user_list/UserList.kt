package com.maxmartin.github.user_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maxmartin.github.models.ListUser
import timber.log.Timber

@Composable
fun UserListHost(
    viewModel: GithubUserListViewModel,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    val users = viewModel.getUsers().collectAsLazyPagingItems()

    UserList(
        modifier = modifier,
        loading = false,
        listUsers = users,
        onClick = onClick
    )
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    loading: Boolean,
    listUsers: LazyPagingItems<ListUser>,
    onClick: (String) -> Unit
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
        LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
            items(listUsers.itemCount) { i ->
                listUsers[i]?.let { user ->
                    UserListItem(listUser = user, onClick = { onClick(user.username) })
                }
            }

            when (listUsers.loadState.refresh) {
                is LoadState.Error -> Timber.e((listUsers.loadState.refresh as LoadState.Error).error)
                LoadState.Loading -> item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
fun UserListItem(listUser: ListUser, onClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        onClick = { onClick.invoke(listUser.username) }
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        val imageModifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .align(Alignment.CenterHorizontally)
        if (LocalView.current.isInEditMode) {
            Image(
                modifier = imageModifier
                    .background(Color.LightGray),
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
        } else {
            AsyncImage(
                modifier = imageModifier,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(listUser.avatarUrl)
                    .placeholder(R.drawable.baseline_person_24)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = listUser.username
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(10.dp),
            text = listUser.username,
            fontWeight = FontWeight.Bold,

            style = MaterialTheme.typography.bodyLarge
        )
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview(showBackground = true)
//@Composable
//fun UserListLoadingPreview() {
//    Scaffold {
//        UserList(loading = true, listUsers = emptyList())
//    }
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview(showBackground = true)
//@Composable
//fun UserListPreview() {
//
//    val listUsers = mutableListOf<ListUser>()
//    for (i in 0..25) {
//        listUsers.add(
//            ListUser(
//                id = 1,
//                username = "mmartin101",
//                avatarUrl = ""
//            )
//        )
//    }
//    Scaffold {
//        UserList(loading = false, listUsers = listUsers)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun UserListItemPreview() {
//    UserListItem(
//        listUser = ListUser(
//            id = 1,
//            username = "mmartin101",
//            avatarUrl = ""
//        ),
//        onClick = {}
//    )
//}