package com.maxmartin.github.user_list

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
import com.maxmartin.github.theme.GithubTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileActivity: ComponentActivity() {
    private val viewModel: GithubUserListViewModel by viewModel()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username")
        username?.let { viewModel.getUser(username) } ?: finish()
        setContent {
            GithubTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Github User Profile",
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
                        UserProfile(
                            modifier = Modifier.padding(padding),
                            viewModel = viewModel
                        )
                    }
                )
            }
        }
    }
}