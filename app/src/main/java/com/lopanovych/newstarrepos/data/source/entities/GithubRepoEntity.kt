package com.lopanovych.newstarrepos.data.source.entities

import com.google.gson.annotations.SerializedName
import com.lopanovych.newstarrepos.domain.models.GithubRepo

data class GithubRepoEntity(
    val id: Long,
    val name: String,
    val fullName: String?,
    val owner: GithubUserEntity,
    val description: String? = null,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Long,
) {
    fun toDomainModel() = GithubRepo(
        id = id,
        name = name,
        fullName = fullName.orEmpty(),
        owner = owner.toDomainModel(),
        description = description,
        url = url,
        stargazersCount = stargazersCount
    )
}