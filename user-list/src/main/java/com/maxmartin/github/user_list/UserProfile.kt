package com.maxmartin.github.user_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun UserProfile(viewModel: GithubUserListViewModel, modifier: Modifier) {
    val state = viewModel.userState.collectAsStateWithLifecycle()
    state.value.user?.let { user ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageModifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
            if (LocalView.current.isInEditMode) {
                Image(
                    modifier = imageModifier.background(Color.LightGray),
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentScale = ContentScale.Fit,
                    contentDescription = ""
                )
            } else {
                AsyncImage(
                    modifier = imageModifier,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .placeholder(R.drawable.baseline_person_24)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = user.username
                )
            }
            val space = Modifier.size(10.dp)
            Spacer(modifier = Modifier.size(40.dp))
            val strings = listOf(
                user.name,
                user.username,
                user.bio,
                user.company,
                user.location,
                user.blogUrl,
                "Repos: ${user.publicRepos}",
                "Gists: ${user.publicGists}",
                "Followers: ${user.followers}",
                "Following: ${user.following}"
            )

            strings.forEachIndexed { i, s ->
                if (s.isEmpty().not()) {
                    when (i) {
                        0 -> Text(text = s, style = MaterialTheme.typography.titleLarge)
                        2 -> Text(
                            text = s,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(40.dp, 0.dp)
                        )

                        else -> Text(text = s)
                    }

                    Spacer(modifier = space)
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//private fun UserProfilePreview() {
//    val user = User(
//        id = 123,
//        username = "mmartin101",
//        avatarUrl = "",
//        name = "Max Martin",
//        bio = "Senior Android Developer, fsadfjjlasdk fsad f sd f asd fas df asdf asdf ",
//        location = "Fort Worth, TX",
//        company = "Some Company",
//        blogUrl = "https://www.google.com/",
//        followers = 30,
//        following = 30,
//        publicGists = 0,
//        publicRepos = 10,
//        twitterUsername = ""
//    )
////    UserProfile(user = user)
//}