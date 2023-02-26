package com.lopanovych.newstarrepos.domain.models

data class GithubUser(
    val login: String,
    val id: Long,
    val nodeId: String,
    val avatarUrl: String,
    val url: String,
    val htmlUrl: String,
    val type: String,
)
