package com.maxmartin.github

import android.app.Application
import com.maxmartin.github.api.apiModule
import com.maxmartin.github.repository.repositoryModule
import com.maxmartin.github.user_list.userListModule
import org.koin.core.context.startKoin
import timber.log.Timber

class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            modules(apiModule)
            modules(repositoryModule)
            modules(userListModule)
        }
    }
}