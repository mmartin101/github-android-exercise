package com.maxmartin.github.user_list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxmartin.github.theme.GithubTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.viewmodel.ext.android.viewModel


class GithubUserListActivity : ComponentActivity() {
    private val viewModel: GithubUserListViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
        setContent {
            GithubTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Github User Profiles",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = if (isSystemInDarkTheme()) {
                                        MaterialTheme.colorScheme.background
                                    } else {
                                        MaterialTheme.colorScheme.onPrimary
                                    }
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary

                            ),
                            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                        )
                    },
                    content = { padding ->
                        UserListHost(
                            modifier = Modifier.padding(padding),
                            viewModel = viewModel,
                            onClick = { username ->
                                startActivity(Intent(baseContext, UserProfileActivity::class.java)
                                    .putExtra("username", username))


                            }
                        )
                    }
                )
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
object ScreenB
