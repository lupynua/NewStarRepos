package com.lopanovych.newstarrepos.domain.repositories

import com.lopanovych.newstarrepos.data.source.entities.GithubRepoSearchResponse
import retrofit2.Response

interface GithubRepoRepository {
    suspend fun getGithubRepos(
        fromDateString: String,
        sortCriteria: String,
        order: String,
        page: Int
    ): Response<GithubRepoSearchResponse>
}