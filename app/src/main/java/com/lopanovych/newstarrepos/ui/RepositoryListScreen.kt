package com.lopanovych.newstarrepos.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lopanovych.newstarrepos.RepoListState
import com.lopanovych.newstarrepos.domain.models.GithubRepo
import com.lopanovych.newstarrepos.openUrl
import com.lopanovych.newstarrepos.ui.theme.NewStarReposTheme

@Composable
fun RepositoryListScreen(
    repos: List<GithubRepo>,
    context: Context,
    lazyListState: LazyListState,
    repoListState: RepoListState
) {
    NewStarReposTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn(state = lazyListState) {
                item {
                    Text(
                        text = "Most starred Github repos created in past month",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                itemsIndexed(items = repos) { _, item ->
                    RepositoryListItem(repository = item) {
                        openUrl(context, item.url)
                    }
                }
                item(
                    key = repoListState,
                ) {
                    when (repoListState) {
                        RepoListState.LOADING -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                        }
                        RepoListState.EXHAUSTED -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "End of the list.")
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}