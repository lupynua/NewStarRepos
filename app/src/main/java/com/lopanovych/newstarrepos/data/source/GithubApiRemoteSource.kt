package com.lopanovych.newstarrepos.data.source

import com.lopanovych.newstarrepos.data.services.GithubRepoService
import com.lopanovych.newstarrepos.data.source.entities.GithubRepoSearchResponse
import retrofit2.Response

class GithubApiRemoteSource(
    private val githubRepoService: GithubRepoService
) {
    suspend fun getGithubRepos(
        fromDateString: String,
        sortCriteria: String,
        order: String,
        page: Int,
    ) : Response<GithubRepoSearchResponse> =
        githubRepoService.searchRepositories(
            query = "created:>$fromDateString",
            sort = sortCriteria,
            order = order,
            page = page
        )
}