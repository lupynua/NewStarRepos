package com.lopanovych.newstarrepos.data.source.entities

import com.google.gson.annotations.SerializedName

data class GithubRepoSearchResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean,
    val items: List<GithubRepoEntity>,
)
