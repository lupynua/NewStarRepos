package com.lopanovych.newstarrepos.presentation.di

import com.lopanovych.newstarrepos.RootViewModel
import com.lopanovych.newstarrepos.data.repositories.GithubRepoRepositoryImpl
import com.lopanovych.newstarrepos.data.services.GithubRepoService
import com.lopanovych.newstarrepos.data.source.GithubApiRemoteSource
import com.lopanovych.newstarrepos.domain.repositories.GithubRepoRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"


fun appModule() = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        val okHttpClient = get<OkHttpClient>()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<GithubRepoService> {
        get<Retrofit>().create(GithubRepoService::class.java)
    }

    factory { GithubApiRemoteSource(get()) }

    factory<GithubRepoRepository> {
        GithubRepoRepositoryImpl(
            remoteSource = get()
        )
    }

    viewModel {
        RootViewModel(githubRepository = get(), ioDispatcher = Dispatchers.IO)
    }

}