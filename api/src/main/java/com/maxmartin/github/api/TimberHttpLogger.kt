package com.maxmartin.github.api

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class TimberHttpLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.i(message)
    }
}