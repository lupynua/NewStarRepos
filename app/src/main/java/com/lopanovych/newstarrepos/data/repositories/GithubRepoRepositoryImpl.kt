package com.lopanovych.newstarrepos.data.repositories

import com.lopanovych.newstarrepos.data.source.GithubApiRemoteSource
import com.lopanovych.newstarrepos.data.source.entities.GithubRepoSearchResponse
import com.lopanovych.newstarrepos.domain.repositories.GithubRepoRepository
import retrofit2.Response

class GithubRepoRepositoryImpl(
    private val remoteSource: GithubApiRemoteSource
) : GithubRepoRepository {

    override suspend fun getGithubRepos(
        fromDateString: String,
        sortCriteria: String,
        order: String,
        page: Int
    ) : Response<GithubRepoSearchResponse> =
        remoteSource.getGithubRepos(fromDateString, sortCriteria, order, page)
}