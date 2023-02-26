package com.lopanovych.newstarrepos.data.source.entities

import com.google.gson.annotations.SerializedName
import com.lopanovych.newstarrepos.domain.models.GithubUser

data class GithubUserEntity(
    val login: String,
    val id: Long,
    @SerializedName("node_url")
    val nodeId: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val type: String,
) {
    fun toDomainModel(): GithubUser = GithubUser(login, id, nodeId.orEmpty(), avatarUrl, url, htmlUrl, type)
}