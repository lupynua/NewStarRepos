package com.lopanovych.newstarrepos.domain.models

data class GithubRepo(
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: GithubUser,
    val description: String?,
    val url: String,
    val stargazersCount: Long,
)
