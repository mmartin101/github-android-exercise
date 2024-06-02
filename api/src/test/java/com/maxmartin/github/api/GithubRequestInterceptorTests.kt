package com.maxmartin.github.api

import okhttp3.Call
import okhttp3.Connection
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.TimeUnit

class GithubRequestInterceptorTests {
    @Test
    fun `should inject necessary headers`() {
        val response = GithubRequestInterceptor()
            .intercept(DummyChain())

        Assert.assertTrue(response.headers["Accept"] == "application/vnd.github+json")
        Assert.assertTrue(response.headers["Authorization"] == "Bearer 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")
        Assert.assertTrue(response.headers["X-GitHub-Api-Version"] == "2022-11-28")
    }

    class DummyChain : Interceptor.Chain {
        override fun call(): Call {
            TODO("Not yet implemented")
        }

        override fun connectTimeoutMillis(): Int {
            TODO("Not yet implemented")
        }

        override fun connection(): Connection? {
            TODO("Not yet implemented")
        }

        override fun proceed(request: Request): Response {
            return Response.Builder()
                .protocol(Protocol.HTTP_2)
                .message("")
                .request(request)
                .headers(request.headers)
                .code(200)
                .build()
        }

        override fun readTimeoutMillis(): Int {
            TODO("Not yet implemented")
        }

        override fun request(): Request {
            return Request.Builder()
                .url("https://dummyurl.com")
                .build()
        }

        override fun withConnectTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
            TODO("Not yet implemented")
        }

        override fun withReadTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
            TODO("Not yet implemented")
        }

        override fun withWriteTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
            TODO("Not yet implemented")
        }

        override fun writeTimeoutMillis(): Int {
            TODO("Not yet implemented")
        }
    }
}