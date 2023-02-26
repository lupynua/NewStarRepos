package com.lopanovych.newstarrepos.data.services

import com.lopanovych.newstarrepos.data.source.entities.GithubRepoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepoService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int
    ): Response<GithubRepoSearchResponse>
}