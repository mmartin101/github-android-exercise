package com.maxmartin.github

import android.app.Application
import com.maxmartin.github.api.apiModule
import com.maxmartin.github.repository.repositoryModule
import org.koin.core.context.startKoin

class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(apiModule)
            modules(repositoryModule)
        }
    }
}