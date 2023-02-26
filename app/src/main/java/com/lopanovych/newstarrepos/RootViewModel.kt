package com.lopanovych.newstarrepos

import androidx.compose.runtime.*
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
    val repos = mutableStateListOf<GithubRepo>()

    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var state by mutableStateOf(RepoListState.IDLE)


    fun getLatestMostStarredRepos() {
        val dateFromString = LocalDate.now().minusMonths(1).format(dateFormat)
        viewModelScope.launch(ioDispatcher) {
            if (page == 1 || (page != 1 && canPaginate) && state == RepoListState.IDLE) {
                state = RepoListState.LOADING

                val response =
                    githubRepository.getGithubRepos(dateFromString, "stars", "desc", page)
                if (response.isSuccessful) {
                    val repoList = response.body()?.items?.map { it.toDomainModel() } ?: emptyList()
                    repos.addAll(repoList)
                    canPaginate = repoList.size == PAGE_SIZE

                    if (page == 1) {
                        repos.clear()
                        repos.addAll(repoList)
                    } else {
                        repos.addAll(repoList)
                    }

                    state = RepoListState.IDLE

                    if (canPaginate)
                        page++
                } else {
                    state =
                        if (page == 1) RepoListState.ERROR else RepoListState.EXHAUSTED
                }
            }
        }
    }

    override fun onCleared() {
        page = 1
        state = RepoListState.IDLE
        canPaginate = false
        super.onCleared()
    }
}