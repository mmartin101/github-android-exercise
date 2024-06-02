package com.maxmartin.github.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor for adding the necessary headers for accessing Github's API
 *
 * Adds the following headers
 * - Accept: application/vnd.github+json
 * - Authorization: Bearer <YOUR-TOKEN>
 * - X-GitHub-Api-Version: 2022-11-28
 */
class GithubRequestInterceptor: Interceptor {
    private val token = "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"
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("X-GitHub-Api-Version", "2022-11-28")

        return chain.proceed(requestBuilder.build())
    }
}