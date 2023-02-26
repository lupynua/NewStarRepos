package com.lopanovych.newstarrepos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lopanovych.newstarrepos.domain.models.GithubRepo
import com.lopanovych.newstarrepos.domain.repositories.GithubRepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val PAGE_SIZE = 30

class RootViewModel(
    private val githubRepository: GithubRepoRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val repos: MutableState<List<GithubRepo>> = mutableStateOf(emptyList())

    init {
        getLatestMostStarredRepos(1)
    }

    fun getLatestMostStarredRepos(page: Int) {
        val dateFromString = LocalDate.now().minusMonths(1).format(dateFormat)
        viewModelScope.launch(ioDispatcher) {
            val response = githubRepository.getGithubRepos(dateFromString, "stars", "desc", page)
            if(response.isSuccessful) {
                repos.value = response.body()?.items?.map { it.toDomainModel() } ?: repos.value
            }
        }
    }
}