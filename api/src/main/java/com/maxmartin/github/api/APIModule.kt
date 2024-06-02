package com.maxmartin.github.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(GithubRequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor(TimberHttpLogger()).setLevel(BODY))
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .baseUrl("https://api.github.com/")
    }
    single { get<Retrofit>().create(GithubAPI::class.java) }
}